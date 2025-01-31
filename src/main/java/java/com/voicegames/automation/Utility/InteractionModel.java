package java.com.voicegames.automation.Utility;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
public class InteractionModel {
	public static JSONObject valNameObj;
	public static HashMap<String, HashMap<String, ?>> intentHashMap = new HashMap<>();
	public static HashMap<String, HashMap<String, ?>> interactionModelHashMap = new HashMap<>();

@Test
public static void interactionModel() throws IOException, ParseException {

	JSONParser jsonparser = new JSONParser();
	@SuppressWarnings("deprecation")
	FileReader reader = new FileReader(Constants.Interactionmodalpath);
	JSONObject jsonObj = (JSONObject) jsonparser.parse(reader);
	JSONObject interactionObj = (JSONObject) jsonObj.get("interactionModel");
	JSONObject languageObject = (JSONObject) interactionObj.get("languageModel");
	JSONArray slotsArray = (JSONArray) languageObject.get("types");
	JSONArray intentsArray = (JSONArray) languageObject.get("intents");
	HashMap<String, HashMap> slotTypesHashMap = new HashMap<>();
	HashMap<String, String[]> intentsHashMap = new HashMap<>();

	Iterator<JSONObject> slotIterator = slotsArray.iterator();
	Iterator<JSONObject> intentIterator = intentsArray.iterator();
	while (slotIterator.hasNext()) {
		JSONObject nameobject = slotIterator.next();
		String name = (String) nameobject.get("name");
//		System.out.println("Name object values: " + name);

		JSONArray slotValues = (JSONArray) nameobject.get("values"); // second level key (2) - slot type name
//			System.out.println("Values array: " + slotValues);

		HashMap<String, String[]> slotsHashMap = new HashMap<>();

		Iterator<JSONObject> valueIter = slotValues.iterator();
		while (valueIter.hasNext()) {
			JSONObject valObj = valueIter.next();
			JSONObject valNameObj = (JSONObject) valObj.get("name");

			JSONArray ansValueArr = (JSONArray) valNameObj.get("synonyms"); // lowest level value (3) - slot values
			String ansVal = (String) valNameObj.get("value"); // lowest level key (3)
//			System.out.println("ansVal: " + ansVal + " , ansValueArr: " + ansValueArr);
			if (null != ansValueArr) {
				int synArrLen = ansValueArr.size();
				String synArr[] = new String[synArrLen];
				int index = 0;
				for (Object syn : ansValueArr.toArray()) {
					synArr[index] = syn.toString();
					index++;
				}
				slotsHashMap.put(ansVal, synArr);
//				System.out.println("print slottype hashmap" + slotsHashMap);
			}
		}
		slotTypesHashMap.put(name, slotsHashMap);
//		System.out.println("print slottype hashmap" + slotTypesHashMap);
	}
	
	while (intentIterator.hasNext()) {
		JSONObject intentObj = intentIterator.next();
		String name = (String) intentObj.get("name");
		//System.out.println("Name object values of Answer intent: " + name);
		JSONArray samplesArray = (JSONArray) intentObj.get("samples");
//		System.out.println("Sample  array: " + samplesArray);
		//String sampleVal = (String) nameobject.get("samples"); // lowest level key (3)
		System.out.println("SampleVal: " + name + " , SampleArr: " + samplesArray);
		if (null != samplesArray) {
			int samplesArrLen = samplesArray.size();
			String[] samplesArr = new String[samplesArrLen];
			int index = 0;
			for(Object syn : samplesArray.toArray()) {
				samplesArr[index] = syn.toString();
				index++;
			}
			intentsHashMap.put(name, samplesArr);
		}			
	}
	
	
	interactionModelHashMap.put("slots", slotTypesHashMap);
	interactionModelHashMap.put("intents", intentsHashMap);

}

public static String findSynonyms(String slotType, String value) throws IOException, ParseException {
	value = value.toLowerCase().trim();
//	System.out.println(interactionModelHashMap);

	HashMap<String, ?> slotsHashmap = interactionModelHashMap.get("slots");
	if (slotsHashmap == null)
		interactionModel();
	System.out.println("printing slot values" + slotsHashmap);
	try {
		HashMap<String, ?> slotTypes = (HashMap<String, ?>) slotsHashmap.get(slotType);
		System.out.println("Before Synonyms");
		String[] synonyms = (String[]) slotTypes.get(value);
		if (synonyms == null || synonyms.length < 1) {
			return value;
		}
		System.out.println("Reached before random");
		Random random = new Random();
		int randomIndex = random.nextInt(synonyms.length);
		return synonyms[randomIndex];
	} catch (Exception e) {
		System.err.println(e);
		System.out.println("Returning value as is due to failure.");
	} finally {
		System.out.println("Execution of findSynonyms Complete");
	}
	return value;
}

public static String[] getStates(String slotType) throws IOException, ParseException {
//	System.out.println(interactionModelHashMap);

	HashMap<String, ?> slotsHashmap = interactionModelHashMap.get("slots");
	if (slotsHashmap == null)
		interactionModel();
	System.out.println("printing slot values" + slotsHashmap);
	try {
		HashMap<String, ?> slotTypes = (HashMap<String, ?>) slotsHashmap.get(slotType);
		System.out.println("Before Synonyms");
		Set<String> states = slotTypes.keySet();
		if (states.size() < 1) {
			throw new Error("State not found in interaction model");
		}

		int n = states.size();
		String statesArr[] = new String[n];

		int i = 0;
		for (String x : states)
			statesArr[i++] = x;

		return statesArr;
	} catch (Exception e) {
		System.err.println(e);
	} finally {
		System.out.println("Returning value as is due to failure.");
	}
	return new String[1];
}

public static String findUtterances(String intentName, String includeSlot) throws IOException, ParseException {
//	System.out.println(interactionModelHashMap.get("intents").get("LEADERBOARD_FOR_STATE"));

	HashMap<String, ?> intentsHashmap = interactionModelHashMap.get("intents");
	if (intentsHashmap == null)
		interactionModel();
	System.out.println("printing intents Value" + intentsHashmap);
	try {
		String[] samples = (String[]) intentsHashmap.get(intentName);
		System.out.println(samples);
//		System.out.println("Before Synonyms");
//		String[] synonyms = (String[]) sampleUtterances.get(intentName);
		if (samples == null || samples.length < 1) {
			return "";
		}
		System.out.println("Reached before random");
		String retVal = _random(samples);
		boolean exhaustSearch = false;
		int loopCount = 0;
		while(!retVal.contains(includeSlot) && !exhaustSearch) {
			retVal = _random(samples);
			loopCount++;
			if(loopCount > 100) {
				exhaustSearch = true;
				retVal = "";
				break;
			}
		}
		return retVal;
	} catch (Exception e) {
		System.err.println(e);
		System.out.println("Returning value as is due to failure.");
	} finally {
		System.out.println("Execution of findUtterances Complete");
	}
	return "";
}

private static String _random(String[] arr) {
	Random random = new Random();
	int randomIndex = random.nextInt(arr.length);
	return arr[randomIndex];
}
}