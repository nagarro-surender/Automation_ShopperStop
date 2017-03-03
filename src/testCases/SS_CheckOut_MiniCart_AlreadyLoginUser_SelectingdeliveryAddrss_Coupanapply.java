package testCases;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;

//import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.BaseClass;
import pageObjects.Cart_Page;
import pageObjects.Checkout_Page;
import pageObjects.MiniCart_Page;
import pageObjects.ProductDetails_Page;
import pageObjects.ProductListing_Page;
import appModules.Cart_Action;
import appModules.CheckOut_Action;
import appModules.HomePage_Action;
//import pageObjects.Home_Page;
import appModules.Login_App;
import appModules.PDP_Action;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;

//import utility.PDFReport;
import utility.Utils;
/**
 * 
 * <h2 style="text-align:center;">SS_CheckOut_AlreadyLoginUser_OrderUsingNetBanking</h2>
 * <p style="font-size:19px"><b>Description -</b>This Test Case verifies checkout flow using Netbanking for already logged in user</p>
 * <TABLE width="100%" border="1">
 * <caption style="font-size:17px">List of columns used from excel file</caption>
 * <tr><th>Parameters</th><th>Description</th></tr>
 * <tr><td>browser</td><td>Browser name in which test execution starts</td></tr>
 * <tr><td>emailId</td><td>Email id of the registered user</td></tr>
 * <tr><td>password</td><td>Password for the registered user</td></tr> 
 * <tr><td>productCategory</td><td>Product Main Category(e.g. MEN, WOMEN etc)</td></tr>
 * <tr><td>productSubCategory</td><td>Product Sub Category(e.g. T-shirt, Watches etc)</td></tr> 
 * <tr><td>firstName</td><td>First Name for the delivery address</td></tr> 
 * <tr><td>lastName</td><td>Last Name for the delivery address</td></tr>
 * <tr><td>postCode</td><td>Postal code for the delivery address</td></tr> 
 * <tr><td>address</td><td>Address field for the delivery</td></tr> 
 * <tr><td>landmark</td><td>Address2 field for the delivery</td></tr> 
 * <tr><td>mobileNumber</td><td>Phone for the delivery address</td></tr>
 * <tr><td>paymentOption</td><td>NetBanking</td></tr>
 * <tr><td>netbankingBankSelection</td><td>Bank selection criteria('SelectBankFromDropdown' in case selected bank is from the drop down list, 'SelectBankFromOptionList' in case selected bank is from the visible list)</td></tr> 
 * <tr><td>bank</td><td>Bank through which payment is to be done</td></tr>
 * </table>
 * <br>
 * <br>
 * 
 */ 


public class SS_CheckOut_MiniCart_AlreadyLoginUser_SelectingdeliveryAddrss_Coupanapply {

	public WebDriver Driver;
	private String sTestCaseName;
	private int iTestCaseRow;


	@BeforeMethod
	public void BeforeMethod() throws Exception {
		DOMConfigurator.configure("log4j.xml");
		sTestCaseName = Utils.getTestCaseName(this.toString());
		Log.info("Test case to be executed: " + sTestCaseName);
		ExcelUtils.setExcelFile(Utils.ReadProperties(Constant.Path_ConfigProperties).getProperty("Path_TestData")
				+ Constant.File_TestData, "Sheet1");
		iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName, Constant.testCaseName);

		Log.startTestCase(sTestCaseName);
		Driver = Utils.OpenBrowser(iTestCaseRow);
		new BaseClass(Driver);
	}

	@Test
	public void main() throws Exception {
		try {
			String BeforePrice ="";
			String AfterPrice ="";
			String AfterPricesub ="";
			String BeforePricesub="";
			Login_App.execute(iTestCaseRow);
			HomePage_Action.selectProductCategoryfromMenu(iTestCaseRow);
			Log.info("Product category selected successfully");
			ProductListing_Page.product().click();
			Log.info("Product selected successfully");
			PDP_Action.product_selectSize(ProductDetails_Page.Product.size_variant_buttonlist());
			ProductDetails_Page.Product.Product_AddToCart().click();
			Log.info("Add to cart button is clicked");

			Utils.verifyElement(MiniCart_Page.MiniCartWindow());
			Log.info("Product is added to the cart and mini cart is displayed");

			
			//MiniCart_Page.MiniCartProductDetails.MiniCartViewBag().click();
			//Log.info("View bag button is clicked on Mini cart window");
		
			Utils.verifyElement(MiniCart_Page.MiniCartProductDetails.MiniCartCheckOutButton());
			MiniCart_Page.MiniCartProductDetails.MiniCartCheckOutButton().click();
			Thread.sleep(5000);
			//Log.info("Checkout button is clicked on cart page");
			//Utils.verifyElement(Checkout_Page.TopNavigation.CheckOutText());
			//Log.info("User successfully reached to Checkout page");
			Checkout_Page.Paymentinfo.ProceedToPaymentButton().click();
	
			
			Cart_Action.Verify_Private_Cart_Page_ApplyCoupan(iTestCaseRow);
			
			//BeforePrice=Cart_Page.TotalCartPriceViewPage().getText();
			//System.out.println(BeforePrice);
			//Cart_Page.CheckoutButtonOnCart().click();
			//CheckOut_Action.CheckOut_Edit_Address(iTestCaseRow);
			//Thread.sleep(5000);
			//Checkout_Page.LoginDetails.ContinueBtn().click();
			//AfterPrice= Cart_Page.TotalCartPriceCheckout().getText();
			//System.out.println(AfterPrice);
			//System.out.println(AfterPrice.substring(11, 16));
			//AfterPricesub=AfterPrice.substring(11, 16);
			//System.out.println(BeforePrice.substring(3, 8));
			//BeforePricesub=BeforePrice.substring(3, 8);
			//AfterPrice.substring(beginIndex, endIndex)
			//Cart_Page.TotalCartPriceDropDown();
			
			//Thread.sleep(5000);
			//Cart_Action.Verify_Private_Cart_Page_CheckPriceFinal(iTestCaseRow);
			//totalPriceLast=Cart_Page.TotalCartPriceCheckout().getAttribute("content");
			//System.out.println(totalPrice3);
			//System.out.println(Cart_Page.TotalCartPriceCheckout().getAttribute("content"));
			
			
			//if (!(BeforePricesub.equals(AfterPricesub))) {
			//	BaseClass.errorValidation += "Coupon coded discount not applied. \n";
			//}
			
			//Cart_Page.RemoveCouponCode().click();
		
			
	
			
			///ExcelUtils.setCellData("Pass", iTestCaseRow, Constant.result);
			//Utils.captureScreenshot(sTestCaseName, "Pass", "Passed");
			//Log.info("Payment successful using netbanking after login at checkout");

		} catch (Exception e) {
			Log.error("Issue in making payment using Netbanking");
			ExcelUtils.setCellData("Fail", iTestCaseRow, Constant.result);

			Utils.captureScreenshot(sTestCaseName, "Fail", "Failure");

			Log.error(e.getMessage());

			throw (e);
		}
	}

	@AfterMethod
	public void afterMethod() {

		Log.endTestCase(sTestCaseName);

		Driver.close();
		Driver.quit();

	}


}
