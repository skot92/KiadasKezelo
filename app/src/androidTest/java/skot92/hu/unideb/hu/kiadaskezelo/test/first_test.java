package skot92.hu.unideb.hu.kiadaskezelo.test;

import skot92.hu.unideb.hu.kiadaskezelo.ui.activity.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class first_test extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public first_test() {
		super(MainActivity.class);
  	}

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
        //Take screenshot
        solo.takeScreenshot();
        //Wait for activity: 'skot92.hu.unideb.hu.kiadaskezelo.ui.activity.MainActivity'
		solo.waitForActivity(skot92.hu.unideb.hu.kiadaskezelo.ui.activity.MainActivity.class, 2000);
        //Click on Új bevétel
		solo.clickOnText(java.util.regex.Pattern.quote("Új bevétel"));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.etInComeName));
        //Enter the text: 'gghhh'
		solo.clearEditText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.etInComeName));
		solo.enterText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.etInComeName), "gghhh");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.etInComePrice));
        //Enter the text: '6676'
		solo.clearEditText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.etInComePrice));
		solo.enterText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.etInComePrice), "6676");
        //Click on Dátum
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.myDatePickerButton));
        //Wait for dialog
		solo.waitForDialogToOpen(5000);
        //Click on Beállítás
		solo.clickOnView(solo.getView(android.R.id.button1));
        //Click on Mentés
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.btnAddNewInCome));
        //Click on Új kiadás
		solo.clickOnText(java.util.regex.Pattern.quote("Új kiadás"));
        //Click on Új termék
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.btnAddNewItem));
        //Wait for dialog
		solo.waitForDialogToOpen(5000);
        //Click on Empty Text View
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTDetailsName));
        //Enter the text: 'zz'
		solo.clearEditText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTDetailsName));
		solo.enterText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTDetailsName), "zz");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTdetailsAmount));
        //Enter the text: '666'
		solo.clearEditText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTdetailsAmount));
		solo.enterText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTdetailsAmount), "666");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTDetailsDescrption));
        //Enter the text: 'tzz'
		solo.clearEditText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTDetailsDescrption));
		solo.enterText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTDetailsDescrption), "tzz");
        //Click on OK
		solo.clickOnView(solo.getView(android.R.id.button1));
        //Click on Új termék
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.btnAddNewItem));
        //Wait for dialog
		solo.waitForDialogToOpen(5000);
        //Click on Empty Text View
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTDetailsName));
        //Enter the text: 'zuu'
		solo.clearEditText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTDetailsName));
		solo.enterText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTDetailsName), "zuu");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTDetailsDescrption));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTDetailsDescrption));
        //Enter the text: 'ttf'
		solo.clearEditText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTDetailsDescrption));
		solo.enterText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTDetailsDescrption), "ttf");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTdetailsAmount));
        //Enter the text: '555'
		solo.clearEditText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTdetailsAmount));
		solo.enterText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTdetailsAmount), "555");
        //Click on OK
		solo.clickOnView(solo.getView(android.R.id.button1));
        //Click on Új termék
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.btnAddNewItem));
        //Wait for dialog
		solo.waitForDialogToOpen(5000);
        //Click on Empty Text View
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTDetailsName));
        //Enter the text: 'uuz'
		solo.clearEditText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTDetailsName));
		solo.enterText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTDetailsName), "uuz");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTDetailsDescrption));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTdetailsAmount));
        //Enter the text: '555'
		solo.clearEditText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTdetailsAmount));
		solo.enterText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTdetailsAmount), "555");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTDetailsDescrption));
        //Enter the text: 'rtt'
		solo.clearEditText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTDetailsDescrption));
		solo.enterText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTDetailsDescrption), "rtt");
        //Click on OK
		solo.clickOnView(solo.getView(android.R.id.button1));
        //Click on Mentés
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.btnSaveExpense));
        //Wait for dialog
		solo.waitForDialogToOpen(5000);
        //Click on Empty Text View
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTExpenseName));
        //Enter the text: 'zuz'
		solo.clearEditText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTExpenseName));
		solo.enterText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.eTExpenseName), "zuz");
        //Click on Dátum
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.myDatePickerButton1));
        //Wait for dialog
		solo.waitForDialogToOpen(5000);
        //Click on Beállítás
		solo.clickOnView(solo.getView(android.R.id.button1));
        //Click on OK
		solo.clickOnView(solo.getView(android.R.id.button1));
        //Click on Mentés
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.btnSaveExpense));
        //Wait for dialog
		solo.waitForDialogToOpen(5000);
        //Click on OK
		solo.clickOnView(solo.getView(android.R.id.button1));
        //Click on Új termék
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.btnAddNewItem));
        //Wait for dialog
		solo.waitForDialogToOpen(5000);
        //Click on OK
		solo.clickOnView(solo.getView(android.R.id.button1));
        //Click on Új bevétel
		solo.clickOnText(java.util.regex.Pattern.quote("Új bevétel"));
        //Click on Mentés
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.btnAddNewInCome));
        //Click on Megtekintés
		solo.clickOnText(java.util.regex.Pattern.quote("Megtekintés"));
        //Click on Bevételek
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.btAllInCome));
        //Wait for activity: 'skot92.hu.unideb.hu.kiadaskezelo.ui.activity.all.AllIncomeActivity'
		assertTrue("skot92.hu.unideb.hu.kiadaskezelo.ui.activity.all.AllIncomeActivity is not found!", solo.waitForActivity(skot92.hu.unideb.hu.kiadaskezelo.ui.activity.all.AllIncomeActivity.class));
        //Click on Rendezés
		solo.clickInList(1, 0);
        //Click on Összeg
		solo.clickOnView(solo.getView(android.R.id.text1));
        //Click on Rendezés
		solo.clickInList(1, 0);
        //Assert that: 'Rendezés' is shown
		assertTrue("'Rendezés' is not shown!", solo.waitForView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.alertTitle)));
        //Click on Összeg
		solo.clickOnView(solo.getView(android.R.id.text1));
        //Click on Rendezés
		solo.clickInList(1, 0);
        //Click on Név
		solo.clickOnView(solo.getView(android.R.id.text1, 2));
        //Click on Keresés
		solo.clickInList(2, 0);
        //Click on Összeg
		solo.clickOnView(solo.getView(android.R.id.text1));
        //Wait for dialog
		solo.waitForDialogToOpen(5000);
        //Click on Kisebb
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.radioLess));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.dialogSum));
        //Enter the text: '6676'
		solo.clearEditText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.dialogSum));
		solo.enterText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.dialogSum), "6676");
        //Click on Keresés
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.btnDisplay));
        //Click on Keresés
		solo.clickInList(2, 0);
        //Click on Összeg
		solo.clickOnView(solo.getView(android.R.id.text1));
        //Wait for dialog
		solo.waitForDialogToOpen(5000);
        //Click on Nagyobb
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.readioGreater));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.dialogSum));
        //Enter the text: '1'
		solo.clearEditText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.dialogSum));
		solo.enterText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.dialogSum), "1");
        //Click on Keresés
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.btnDisplay));
        //Click on Keresés
		solo.clickInList(2, 0);
        //Click on Összeg
		solo.clickOnView(solo.getView(android.R.id.text1));
        //Wait for dialog
		solo.waitForDialogToOpen(5000);
        //Click on Keresés
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.btnDisplay));
        //Press menu back key
		solo.goBack();
        //Wait for dialog to close
		solo.waitForDialogToClose(5000);
        //Click on Keresés
		solo.clickInList(2, 0);
        //Click on Név
		solo.clickOnView(solo.getView(android.R.id.text1, 2));
        //Wait for dialog
		solo.waitForDialogToOpen(5000);
        //Click on Empty Text View
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.incomeName));
        //Enter the text: 'gghhh'
		solo.clearEditText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.incomeName));
		solo.enterText((android.widget.EditText) solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.incomeName), "gghhh");
        //Click on Keresés
		solo.clickOnView(solo.getView(skot92.hu.unideb.hu.kiadaskezelo.R.id.btnDisplay));
        //Press menu back key
		solo.goBack();
	}
}
