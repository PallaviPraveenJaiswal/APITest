package ApiProject.Demo;

import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.List;
import  io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class GetRequest {
	static JsonPath js;

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://raw.githubusercontent.com/stockholmux/ecommerce-sample-set/master/items.json";
		
		String getRes = 
				given().log().all()
				.header("Content-Type", "application/json")
				.when()
				.get().then()
				//.log().all()
				.assertThat()
				.statusCode(200)
				.extract()
				.asString();
		
	
		 js = new JsonPath(getRes);
		
		ArrayList<String> items = js.get("$");
		
		System.out.println("List of all items are ------->" + items);
		System.out.println("Total size of listed item is: "+js.getList("itemType").size());
		
		List<String> allItems = js.getList("itemType");
		System.out.println("Total listed items are: "+allItems);
		
		List<String> priceList = js.getList("price");
		System.out.println("All Items listed price are: "+priceList);
		System.out.println("");
		
	
		System.out.println("..........The 1st question answers are provided below...........");		
		System.out.println("");
		
		
		List<String> GreaterPriceList = js.getList("findAll{it.price.toDouble()== 19.99}.itemType");
		
		System.out.println("The number of items in this list with a price of 19.99 : "+GreaterPriceList);
		
		System.out.println("1(a): Total number of item with a price of 19.99 are: "+GreaterPriceList.size());
		
		String highestPricedItem = js.get("max {it.price}.itemType");
		System.out.println("1(b): The highest priced item name is:  "+highestPricedItem);
		
		System.out.println("");
		System.out.println("");
		
	
		System.out.println("..........The 2nd question answers are provided below...........");	
		System.out.println("");
		
		List<String> SecondPriceList = js.getList("findAll{it.price.toDouble()== 17.99}.itemType");
		System.out.println("2(a): Total number of item with a price of 17.99 are: "+SecondPriceList.size());
		System.out.println("");
		List<String> allListItems = js.getList("findAll{it.price > 20.00}.itemType");
		System.out.println("2(b-1): Total number of item with a price > 20.00 are: "+allListItems.size());
		System.out.println("2(b-2): The name of items with price > 20.00 are: "+allListItems);
		
		
	}
	
	/* 
	*-------- Test cases are below for both the questions 1(c) & 2(c) ---------------*/
	/*
	 * 1. Will validate the status code is correct or not using assertion
	 * 2. Will validate the response body contains the required data or not & in proper json format.
	 * 3  Will validate the headers metadata as required
	 * 4. Will validate the response against database if needed.
	 * 5. will apply assertion on required places
	 * 6. Will validate the expected responses and make responses are getting filtered based on the requirement.
	 */
	
	
	
	
}
