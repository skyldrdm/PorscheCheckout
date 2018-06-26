package porsche;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PorscheCheckout {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
//		1. Open browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().fullscreen();
//		2. Go to url “https://www.porsche.com/usa/modelstart/”
		String webSite = "https://www.porsche.com/usa/modelstart/";
		driver.get(webSite);
//		3. Select model 718
		String someThing = "718";
		driver.findElement(By.partialLinkText(someThing)).click();
//		4. Remember the price of 718 Cayman
		String priceCayman = driver.findElement(By.className("m-14-model-price")).getText();
		int price718Cayman = Integer.parseInt(priceCayman.substring(7,priceCayman.length()-4).replace(",", ""));
		System.out.println(price718Cayman);
		
//		5. Click on Build & Price under 718 Cayman
		driver.findElement(By.xpath("//*[@id=\"m982120\"]/div[2]/div/a")).click();
		
//		6. Verify that Base price displayed on the page is same as the price from step 4
		
		for (String winHandle : driver.getWindowHandles()) {
		    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}
		
		System.out.println(driver.getTitle());
		Thread.sleep(2000);
		String basePrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[1]/div[2]")).getText();
		System.out.println(basePrice);
		int basePriceActual = Integer.parseInt(basePrice.substring(1).replace(",",""));
		if (price718Cayman==basePriceActual) {
			System.out.println("Step 6 pass");
		} else {
			System.out.println("Step 6 fail");
			System.out.println("Expected price :"+price718Cayman);
			System.out.println("Actual price :"+basePriceActual);
		}
		
//		7. Verify that Price for Equipment is 0
		String equipmentPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		System.out.println(equipmentPrice);
		int equipmentPriceActual = Integer.parseInt(equipmentPrice.substring(1).replace(",",""));
		if (equipmentPriceActual==0) {
			System.out.println("Step 7 pass");
		} else {
			System.out.println("Step 7 fail");
			System.out.println("Expected equipment price :"+0);
			System.out.println("Actual equipment price :"+equipmentPriceActual);
		}
//		8. Verify that total price is the sum of base price + Delivery, Processing and Handling
//		Fee
		String dphFee = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[3]/div[2]")).getText();
		System.out.println(dphFee);
		int dphFeeActual = Integer.parseInt(dphFee.substring(1).replace(",",""));
		String totalPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		System.out.println(totalPrice);
		int totalPriceActual = Integer.parseInt(totalPrice.substring(1).replace(",",""));
		if (totalPriceActual==(basePriceActual+dphFeeActual)) {
			System.out.println("Step 8 pass");
		} else {
			System.out.println("Step 8 fail");
			System.out.println("Expected total price :"+(basePriceActual+dphFeeActual));
			System.out.println("Actual total price :"+totalPriceActual);
		}
//		9. Select color “Miami Blue”
		String color = "Miami Blue";
		driver.findElement(By.id("s_exterieur_x_FJ5")).click();
		
//		10.Verify that Price for Equipment is Equal to Miami Blue price
		String colorPrice = driver.findElement(By.id("s_exterieur_x_FJ5")).getAttribute("data-price");
		System.out.println(colorPrice);
		int colorPriceActual = Integer.parseInt(colorPrice.substring(1).replace(",",""));
		equipmentPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		System.out.println(equipmentPrice);
		equipmentPriceActual = Integer.parseInt(equipmentPrice.substring(1).replace(",",""));
		
		if (equipmentPriceActual==colorPriceActual) {
			System.out.println("Step 10 pass");
		} else {
			System.out.println("Step 10 fail");
			System.out.println("Expected Equipment price :"+colorPriceActual);
			System.out.println("Actual Equipment price :"+equipmentPriceActual);
		}
//		11.Verify that total price is the sum of base price + Price for Equipment + Delivery,
//		Processing and Handling Fee
		dphFee = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[3]/div[2]")).getText();
		System.out.println(dphFee);
		dphFeeActual = Integer.parseInt(dphFee.substring(1).replace(",",""));
		totalPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		System.out.println(totalPrice);
		totalPriceActual = Integer.parseInt(totalPrice.substring(1).replace(",",""));
		if (totalPriceActual==(basePriceActual+equipmentPriceActual+dphFeeActual)) {
			System.out.println("Step 11 pass");
		} else {
			System.out.println("Step 11 fail");
			System.out.println("Expected total price :"+(basePriceActual+equipmentPriceActual+dphFeeActual));
			System.out.println("Actual total price :"+totalPriceActual);
		}
//		12.Select 20" Carrera Sport Wheels
		driver.findElement(By.id("s_exterieur_x_M433")).click();
		Thread.sleep(1000);
//		13.Verify that Price for Equipment is the sum of Miami Blue price + 20" Carrera Sport
//		Wheels
		String wheelPrice = driver.findElement(By.id("s_exterieur_x_M433")).getAttribute("data-price");
		System.out.println(wheelPrice);
		int wheelPriceActual = Integer.parseInt(wheelPrice.substring(1).replace(",",""));
		equipmentPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		System.out.println(equipmentPrice);
		equipmentPriceActual = Integer.parseInt(equipmentPrice.substring(1).replace(",",""));
		if (equipmentPriceActual==(wheelPriceActual+colorPriceActual)) {
			System.out.println("Step 13 pass");
		} else {
			System.out.println("Step 13 fail");
			System.out.println("Expected Equipment price :"+(wheelPriceActual+colorPriceActual));
			System.out.println("Actual Equipment price :"+equipmentPriceActual);
		}
//		14.Verify that total price is the sum of base price + Price for Equipment + Delivery,
//		Processing and Handling Fee
		totalPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		System.out.println(totalPrice);
		totalPriceActual = Integer.parseInt(totalPrice.substring(1).replace(",",""));
		if (totalPriceActual==(basePriceActual+equipmentPriceActual+dphFeeActual)) {
			System.out.println("Step 14 pass");
		} else {
			System.out.println("Step 14 fail");
			System.out.println("Expected total price :"+(basePriceActual+equipmentPriceActual+dphFeeActual));
			System.out.println("Actual total price :"+totalPriceActual);
		}
//		15.Select seats ‘Power Sport Seats (14-way) with Memory Package’
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,1000)","");
		driver.findElement(By.id("s_interieur_x_PP06")).click();
		Thread.sleep(1000);
//		16.Verify that Price for Equipment is the sum of Miami Blue price + 20" Carrera Sport
//		Wheels + Power Sport Seats (14-way) with Memory Package
		String seatPrice = driver.findElement(By.xpath("//*[@id=\"seats_73\"]/div[2]/div[1]/div[3]/div")).getText();
		System.out.println(seatPrice);
		int seatPriceActual = Integer.parseInt(seatPrice.substring(1).replace(",",""));
		equipmentPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		System.out.println(equipmentPrice);
		equipmentPriceActual = Integer.parseInt(equipmentPrice.substring(1).replace(",",""));
		if (equipmentPriceActual==(wheelPriceActual+colorPriceActual+seatPriceActual)) {
			System.out.println("Step 16 pass");
		} else {
			System.out.println("Step 16 fail");
			System.out.println("Expected Equipment price :"+(wheelPriceActual+colorPriceActual+seatPriceActual));
			System.out.println("Actual Equipment price :"+equipmentPriceActual);
		}
		
		
//		17.Verify that total price is the sum of base price + Price for Equipment + Delivery,
//		Processing and Handling Fee
		totalPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		System.out.println(totalPrice);
		totalPriceActual = Integer.parseInt(totalPrice.substring(1).replace(",",""));
		if (totalPriceActual==(basePriceActual+equipmentPriceActual+dphFeeActual)) {
			System.out.println("Step 17 pass");
		} else {
			System.out.println("Step 17 fail");
			System.out.println("Expected total price :"+(basePriceActual+equipmentPriceActual+dphFeeActual));
			System.out.println("Actual total price :"+totalPriceActual);
		}
//		18.Click on Interior Carbon Fiber
		jse.executeScript("window.scrollBy(0,1000)","");
		driver.findElement(By.id("IIC_subHdl")).click();
		Thread.sleep(1000);
//		19.Select Interior Trim in Carbon Fiber i.c.w. Standard Interior
		driver.findElement(By.id("vs_table_IIC_x_PEKH_x_c04_PEKH_x_shorttext")).click();
		Thread.sleep(1000);
//		20.Verify that Price for Equipment is the sum of Miami Blue price + 20" Carrera Sport
//		Wheels + Power Sport Seats (14-way) with Memory Package + Interior Trim in
//		Carbon Fiber i.c.w. Standard Interior
		String carbonFiberInteriorPrice = driver.findElement(By.xpath("//*[@id=\"vs_table_IIC_x_PEKH\"]/div[1]/div[2]/div")).getText();
		System.out.println(carbonFiberInteriorPrice);
		int carbonFiberInteriorPriceActual = Integer.parseInt(carbonFiberInteriorPrice.substring(1).replace(",",""));
		equipmentPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		System.out.println(equipmentPrice);
		equipmentPriceActual = Integer.parseInt(equipmentPrice.substring(1).replace(",",""));
		if (equipmentPriceActual==(wheelPriceActual+colorPriceActual+seatPriceActual+carbonFiberInteriorPriceActual)) {
			System.out.println("Step 20 pass");
		} else {
			System.out.println("Step 20 fail");
			System.out.println("Expected Equipment price :"+(wheelPriceActual+colorPriceActual+seatPriceActual+carbonFiberInteriorPriceActual));
			System.out.println("Actual Equipment price :"+equipmentPriceActual);
		}
//		21.Verify that total price is the sum of base price + Price for Equipment + Delivery,
//		Processing and Handling Fee
		totalPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		System.out.println(totalPrice);
		totalPriceActual = Integer.parseInt(totalPrice.substring(1).replace(",",""));
		if (totalPriceActual==(basePriceActual+equipmentPriceActual+dphFeeActual)) {
			System.out.println("Step 21 pass");
		} else {
			System.out.println("Step 21 fail");
			System.out.println("Expected total price :"+(basePriceActual+equipmentPriceActual+dphFeeActual));
			System.out.println("Actual total price :"+totalPriceActual);
		}
//		22.Click on Performance
		driver.findElement(By.id("IMG_subHdl")).click();
		Thread.sleep(1000);
//		23.Select 7-speed Porsche Doppelkupplung (PDK)
		driver.findElement(By.id("vs_table_IMG_x_M250_x_c14_M250_x_shorttext")).click();
		Thread.sleep(1000);
//		24.Select Porsche Ceramic Composite Brakes (PCCB)
		jse.executeScript("window.scrollBy(0,500)","");
		driver.findElement(By.id("vs_table_IMG_x_M450_x_c94_M450_x_shorttext")).click();
		Thread.sleep(1000);
//		25.Verify that Price for Equipment is the sum of Miami Blue price + 20" Carrera Sport
//		Wheels + Power Sport Seats (14-way) with Memory Package + Interior Trim in Carbon Fiber i.c.w. Standard Interior + 7-speed Porsche Doppelkupplung (PDK) + Porsche Ceramic Composite Brakes (PCCB)
		String PDKPrice = driver.findElement(By.xpath("//*[@id=\"vs_table_IMG_x_M250\"]/div[1]/div[2]/div")).getText();
		System.out.println(PDKPrice);
		int PDKPriceActual = Integer.parseInt(PDKPrice.substring(1).replace(",",""));
		String brakesPrice = driver.findElement(By.xpath("//*[@id=\"vs_table_IMG_x_M450\"]/div[1]/div[2]/div")).getText();
		System.out.println(brakesPrice);
		int brakesPriceActual = Integer.parseInt(brakesPrice.substring(1).replace(",",""));
		equipmentPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		System.out.println(equipmentPrice);
		equipmentPriceActual = Integer.parseInt(equipmentPrice.substring(1).replace(",",""));
		if (equipmentPriceActual==(wheelPriceActual+colorPriceActual+seatPriceActual+carbonFiberInteriorPriceActual+PDKPriceActual+brakesPriceActual)) {
			System.out.println("Step 25 pass");
		} else {
			System.out.println("Step 25 fail");
			System.out.println("Expected Equipment price :"+(wheelPriceActual+colorPriceActual+seatPriceActual+carbonFiberInteriorPriceActual+PDKPriceActual+brakesPriceActual));
			System.out.println("Actual Equipment price :"+equipmentPriceActual);
		}
//		26.Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
//		Thread.sleep(1000);
		totalPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		System.out.println(totalPrice);
		totalPriceActual = Integer.parseInt(totalPrice.substring(1).replace(",",""));
		if (totalPriceActual==(basePriceActual+equipmentPriceActual+dphFeeActual)) {
			System.out.println("Step 26 pass");
		} else {
			System.out.println("Step 26 fail");
			System.out.println("Expected total price :"+(basePriceActual+equipmentPriceActual+dphFeeActual));
			System.out.println("Actual total price :"+totalPriceActual);
		}
		
		driver.close();
	}

}
