package TestClasses;

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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class AnswerIntent {
	public static  HashMap<JSONObject, JsonArray> SamplesHashMap = new HashMap<>();
	public static JSONObject valNameObj;
	public static String name;
	public static JSONArray intentArray;
	public static JSONObject languageObject;
	public static void main(String args[]) throws IOException, ParseException {

		interactionModel();
		String[] sampleval = getUttrances("samples");
		//System.out.println(findSynonyms(name, sampleval[0]));
	//	System.out.println(findSynonyms("OPTION_VALUE", "brett favre"));

	}

	public static void interactionModel() throws IOException, ParseException {

		JSONParser jsonparser = new JSONParser();
		@SuppressWarnings("deprecation")
		FileReader reader = new FileReader("C:\\Users\\Admin\\git\\testing-automation\\src\\main\\resources\\File\\newjson.json");
		JSONObject jsonObj = (JSONObject) jsonparser.parse(reader);
		JSONObject interactionObj = (JSONObject) jsonObj.get("interactionModel");
		JSONObject languageObject = (JSONObject) interactionObj.get("languageModel");
		JSONArray slotsArray = (JSONArray) languageObject.get("types");
		intentArray = (JSONArray) languageObject.get("intents");

		System.out.println("Intent array values: " + intentArray);
		HashMap<String, JSONArray> slotTypesHashMap = new HashMap<>();

		Iterator<JSONObject> intentArrayiterator = intentArray.iterator();
		while (intentArrayiterator.hasNext()) {
			JSONObject nameobject = intentArrayiterator.next();
			name = (String) nameobject.get("name");
			//System.out.println("Name object values of Answer intent: " + name);
			intentArray = (JSONArray) languageObject.get("intents");
			System.out.println("Sample  array: " + intentArray);
			//String sampleVal = (String) nameobject.get("samples"); // lowest level key (3)
			System.out.println("SampleVal: " + name + " , SampleArr: " + intentArray);
			if (null != intentArray) {
				int synArrLen = intentArray.size();
				String[] synArr = new String[synArrLen];
				int index = 0;
				for(Object syn : intentArray.toArray()) {
					synArr[index] = syn.toString();
					index++;
				}			
			}			
		}
		slotTypesHashMap.put(name, intentArray);
		System.out.println("print slottype hashmap" + slotTypesHashMap);
	}

	public static String findSynonyms(JsonObject name, JsonArray samArr) throws IOException, ParseException {
	//	value = value.toLowerCase().trim();
		
		intentArray = (JSONArray) languageObject.get("intents");
		JsonArray slotsHashmap = SamplesHashMap.get("name");
		//System.out.println("print slothashmap" + slotsHashmap);
		if (slotsHashmap == null)
			interactionModel();
		//System.out.println("printing slot values" + slotsHashmap);
//		try {
//			HashMap<JSONObject, JSONArray> slotTypes = (HashMap<JSONObject, JSONArray>) slotsHashmap.get(name);
//			System.out.println("Before Synonyms");
//			String[] synonyms = (String[]) slotTypes.get(value);
//			if (synonyms == null || synonyms.length < 1) {
//				return value;
//			}
//			System.out.println("Reached before random");
//			Random random = new Random();
//			int randomIndex = random.nextInt(synonyms.length);
//			return synonyms[randomIndex];
//		} catch (Exception e) {
//			System.err.println(e);
//			System.out.println("Returning value as is due to failure.");
//		} finally {
//			System.out.println("Execution of findSynonyms Complete");
//		}
//		return value;
		return null;
	}

	public static String[] getUttrances(String samples) throws IOException, ParseException {
		
		 System.out.println("Printing sample hashmap " + SamplesHashMap);
		 JSONArray intentArray = (JSONArray) languageObject.get("intents");
		@SuppressWarnings("unlikely-arg-type")
		JsonArray samplesHashmap = SamplesHashMap.get(intentArray);
		if (samplesHashmap == null)
			interactionModel();
		// System.out.println("printing sample values" + samplesHashmap);
		try {
			HashMap<String, String> sampleTypes =new  HashMap<String, String>();
			sampleTypes.get("samples");
			 System.out.println("Before Synonyms");
			Set<String> sample = sampleTypes.keySet();
			if (sample.size() < 1) {
				throw new Error("samples not found in the list");
			}

			int n = sample.size();
			String statesArr[] = new String[n];

			int i = 0;
			for (String x : sample)
				statesArr[i++] = x;
			
			return statesArr;
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			 System.out.println("Returning value as is due to failure.");
		}
		return new String[1];
	}
}
