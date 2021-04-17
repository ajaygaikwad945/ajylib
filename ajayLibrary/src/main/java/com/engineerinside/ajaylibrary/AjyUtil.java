package com.engineerinside.ajaylibrary;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.engineerinside.ajaylibrary.R;

public class AjyUtil {


    //utility-1
    //time format conversion with string value of date,input pattern and output pattern
    public static String timeConversion(Context context, String input, String inputPattern, String outputPattern){
        String output = "";
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat(inputPattern);
            final Date dateObj = sdf.parse(input);
            System.out.println(dateObj);
            output = new SimpleDateFormat(outputPattern).format(dateObj);
        } catch (final ParseException e) {
            e.printStackTrace();
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return output;
    }




    //utility-2
    //time format conversion with date() and output pattern
    public static String timeConversion(Context context,Date input,String outputPattern){
        String output = "";
        output = new SimpleDateFormat(outputPattern).format(input);
        return output;
    }





    public static String timeConversion(Date input,String outputPattern){
        String output = "";
        output = new SimpleDateFormat(outputPattern).format(input);
        return output;
    }




    //utility-3
    //set error to edittext with message
    public static void  requiredEditText(Context context, EditText editText, String msg){
        editText.setError(msg);
        editText.requestFocus();
        return;
    }



    //utility-4
    //remove array braces of string eg.{ [ajay,rutuja] = ajay,rutuja }
    public  static  String removeArrayBrace(String input){
        input = input.replace("[", "").replace("]","");
        input = input.replace(" ", "");
        return input;
    }



    //utility-5
    //return comma separated string to list
    public  static List<String> commaStringToList(String input){
        List<String> items = new ArrayList<>();
        try{
            items = Arrays.asList(input.split("\\s*,\\s*"));
        }catch(Exception e){}
        return items;
    }




    //utility-6
    //return comma separated string to Arraylist
    public  static  ArrayList<String> commaStringToArrayList(String input){
        ArrayList<String> items = new ArrayList<>();
        try{
            items = new ArrayList<String>(Arrays.asList(input.split("\\s*,\\s*")));
        }catch(Exception e){}
        return items;
    }



    //utility-7
    //check empty edittext with error message
    public  static Boolean invalidField(EditText et,String msg){
        if(et.getText().toString().isEmpty() ||et.getText().toString().trim().equals("")){
            et.setError(msg);
            et.requestFocus();
            return false;
        }
        return true;
    }





    //utility-8
    //check empty edittext
    public  static Boolean invalidField(EditText et){
        if(et.getText().toString().isEmpty() ||et.getText().toString().trim().equals("")){
            et.setError("This Field Required");
            et.requestFocus();
            return true;
        }
        return false;
    }



    //utility-9
    //check mobile edittext for empty or length
    public  static Boolean invalidFieldMobile(EditText et){
        if(et.getText().toString().isEmpty() ||et.getText().toString().trim().equals("")){
            et.setError("This Field Required");
            et.requestFocus();
            return true;
        }
        if(et.getText().toString().trim().length()<10){
            et.setError("Enter correct mobile no.");
            et.requestFocus();
            return true;
        }
        return false;
    }



    //utility-10
    //check string for null or empty
    public  static Boolean invalidField(String et){
        if(et.isEmpty() || et.trim().equals("")){
            return true;
        }
        return false;
    }







    //utility-15
    //hide keyboard from activity
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }



    //utility-16
    //hide keyboard from context
    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }



    //utility-17
    //remove char at specific position
    public static String charRemoveAt(String str, int p) {
        return str.substring(0, p) + str.substring(p + 1);
    }



    //utility-18
    //return true once a day
    public static Boolean once_a_day(Context context,String key){
        String currDate = SharedPreference.getInstance().getValue(context,key);
        String currDate1 = timeConversion(context,new Date(),"ddMMyyyy");
        Log.d("currdate11211",currDate1);
        if(currDate.equals(currDate1)){
            return false;
        }else{
            SharedPreference.getInstance().save(context,key,currDate1);
            return true;
        }
    }



    //utility-19
    //calculate discounted amount from initial amount and discount
    public static  String calcuateDiscount(String inputStr,String discountStr){
        double  dis,amount=0.0,markedprice,s;
        try {
            s = 100 - Double.parseDouble(discountStr);
            amount = (s * Double.parseDouble(inputStr)) / 100;
        }catch (Exception e){}

        return String.valueOf(amount);
    }




    //utility-20
    //calculate initial amount from discount and discounted amount
    public static  String calcuateDiscountReverse(String inputStr,String discountStr){
        double  dis,amount=0.0,markedprice,s;
        try {
            amount = Double.parseDouble(inputStr) / (100.00 -(Double.parseDouble(discountStr))) * 100;
        }catch (Exception e){}

        return String.valueOf(amount);
    }




    //utility-21
    //validate discount fields
    // please add in addtextchangelistner
    public static String discountValidation(EditText etDiscount ){

        if(etDiscount.getText().toString().trim().equals("")){

        }else{
            if(Double.parseDouble(etDiscount.getText().toString().trim())>=100.00){
                etDiscount.setError("Discount can not be greater than 100 percent");
                etDiscount.requestFocus();
                etDiscount.setText("");
            }
        }
        return etDiscount.getText().toString();
    }



    //utility-22
    // filter date with strings LiKe = Today, Yesterday, Tomorrow, Last 7 Days, This Week, Last Week, This Month, Last Month , All
    public static List dateFilters(String fil){
        String filDateFrom="";
        String filDateTo="";

        List<String> dates = new ArrayList<>();
        if(fil.equals("All")) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = sdf.format(new Date());

            dates.add("");
            dates.add("");

        }
        if(fil.equals("Today")) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = sdf.format(new Date());
            filDateFrom = date;
            filDateTo = date;

            dates.add(filDateFrom);
            dates.add(filDateTo);

        }
        if(fil.equals("Yesterday"))  {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = sdf.format(currentDateMinus(1));
            filDateFrom = date;
            filDateTo = date;

            dates.add(filDateFrom);
            dates.add(filDateTo);

        }
        if(fil.equals("Tomorrow"))  {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = sdf.format(currentDateMinus(-1));
            filDateFrom = date;
            filDateTo = date;

            dates.add(filDateFrom);
            dates.add(filDateTo);
        }
        if(fil.equals("Last 7 Days")){

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dateFrom = sdf.format(currentDateMinus(7));
            String dateTo = sdf.format(new Date());
            filDateFrom = dateFrom;
            filDateTo = dateTo;

            dates.add(filDateFrom);
            dates.add(filDateTo);
        }

        if(fil.equals("This Week")) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdfDay = new SimpleDateFormat("EEEE");
            String day = sdfDay.format(new Date());
            filDateFrom = sdf.format(currentDateMinus(currentDayMinus(day)));
            filDateTo = sdf.format(new Date());

            dates.add(filDateFrom);
            dates.add(filDateTo);
        }

        if(fil.equals("Last Week")) {
            SimpleDateFormat sdf = new  SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdfDay = new SimpleDateFormat("EEEE");
            String day = sdfDay.format(new Date());
            filDateFrom = sdf.format(currentDateMinus(currentDayMinus(day)+7));
            filDateTo = sdf.format(currentDateMinus(currentDayMinus(day)));

            dates.add(filDateFrom);
            dates.add(filDateTo);
        }

        if(fil.equals("This Month")){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdf1 = new SimpleDateFormat("1/MM/yyyy");
            String dateFrom = sdf1.format(new Date());
            String dateTo = sdf.format(new Date());

            filDateFrom = dateFrom;
            filDateTo = dateTo;
            dates.add(filDateFrom);
            dates.add(filDateTo);
        }

        if(fil.equals("Last Month")){

            SimpleDateFormat sdf = new SimpleDateFormat("1/MM/yyyy");
            SimpleDateFormat sdf1 = new SimpleDateFormat("31/MM/yyyy");
            filDateFrom = sdf.format(currentMonthMinus(1));
            filDateTo = sdf1.format(currentMonthMinus(1));
            dates.add(filDateFrom);
            dates.add(filDateTo);
        }

        if(fil.contains(",")){
            Log.d("fil785",fil);
            String[] filr = fil.split(",");
            dates.add(filr[0]);
            dates.add(filr[1]);
        }
        return  dates;
    }

    //utility-23
    static Date currentDateMinus(int i) {
        Calendar cal =  Calendar.getInstance();
        cal.add(Calendar.DATE, -i);
        return cal.getTime();
    }

    //utility-24
    static Date currentMonthMinus(int i) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -i);
        return cal.getTime();
    }


    //utility-25
    //i=Monday or Tuesday or Wednesday or Thursday or Friday or Saturday or Sunday
    static int currentDayMinus(String i) {

        if(i.equals("Monday")){
            return 1;
        }
        if(i.equals("Tuesday")){
            return 2;
        }
        if(i.equals("Wednesday")){
            return 3;
        }
        if(i.equals("Thursday")){
            return 4;
        }
        if(i.equals("Friday")){
            return 5;
        }
        if(i.equals("Saturday")){
            return 6;
        }
        if(i.equals("Sunday")){
            return 0;
        }
        return 0;
    }





    //utility-26
    //if from date is smaller than to then throws false
    public static boolean fromDateToDateValidation(Context context,String fromString,String toString) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        boolean b = false;
        try {
            if(df.parse(fromString).before(df.parse(toString)))
            {
                // b = true;//If start date is before end date
                b=  true;
            }
            else if(df.parse(fromString).equals(df.parse(toString)))
            {
                // b = true;//If start date is before end date
                b = true;
            }
            else{
                b = false;
            }
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return b;
    }






    public static String compressImage(Context context,String imageUri,int qualityStr) {

        String filePath = getRealPathFromURI(context,imageUri);
        Bitmap scaledBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();

//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

//      max Height and width values of the compressed image is taken as 816x612

        float maxHeight = 816.0f;
        float maxWidth = 612.0f;
        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;

            }
        }

//      setting inSampleSize value allows to load a scaled down version of the original image

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
//          load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                    scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream out = null;
        String filename = getFilename();
        try {
            out = new FileOutputStream(filename);

//          write the compressed bitmap at the destination specified by filename.
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, qualityStr, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return filename;

    }

    public static String getFilename() {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), ".CompressedImages");
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriSting = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
        return uriSting;

    }

    private static String getRealPathFromURI(Context context, String contentURI) {
        Uri contentUri = Uri.parse(contentURI);
        Cursor cursor = context.getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(index);
        }
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }




    /*
    eg:
    input list : [03:00 pm, 02:00 pm, 10:00 am]
    output list : [10:00 am, 02:00 pm, 03:00 pm]
    */
    public static List<String> timeListSorting(List<String> inputList, final String format){

        Collections.sort(inputList, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                try {
                    return new SimpleDateFormat(format).parse(o1).compareTo(new SimpleDateFormat(format).parse(o2));
                } catch (ParseException e) {
                    return 0;
                }
            }
        });

        return inputList;
    }




    public static boolean isURLReachable(Context context,String inputUrl) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            try {
                URL url = new URL(inputUrl);   // Change to "http://google.com" for www  test.
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setConnectTimeout(10 * 1000);          // 10 s.
                urlc.connect();
                if (urlc.getResponseCode() == 200) {        // 200 = "OK" code (http connection is fine).
                    Log.wtf("Connection", "Success !");
                    return true;
                } else {
                    return false;
                }
            } catch (MalformedURLException e1) {
                return false;
            } catch (IOException e) {
                return false;
            }
        }
        return false;
    }




    //input and mapping = Sunday=0,Monday=1,Tuesday=2,Wednesday=3,Thursday=4,Friday=5,Saturday=6
    public static String weekDaysMappking(String input){
        if(input.equals("Sunday")){
            return "0";
        }if(input.equals("Monday")){
            return "1";
        }if(input.equals("Tuesday")){
            return "2";
        }if(input.equals("Wednesday")){
            return "3";
        }if(input.equals("Thursday")){
            return "4";
        }if(input.equals("Friday")){
            return "5";
        }if(input.equals("Saturday")){
            return "6";
        }

        return "9";
    }


    public static void toastError(Activity context,String msg) {

        LayoutInflater li = context.getLayoutInflater();
        View layout = li.inflate(R.layout.toast_layout_error, (ViewGroup) context.findViewById(R.id.custom_toast_layout));
        TextView custom_toast_message = layout.findViewById(R.id.custom_toast_message);
        custom_toast_message.setText(msg);
        final Toast toast = new Toast(context);
        //toast.setDuration(Toast.LENGTH_SHORT);
        //toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setView(layout);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 1000);

    }

    public static void toastError(Activity context,String msg,int time_in_millis) {

        LayoutInflater li = context.getLayoutInflater();
        View layout = li.inflate(R.layout.toast_layout_error, (ViewGroup) context.findViewById(R.id.custom_toast_layout));
        TextView custom_toast_message = layout.findViewById(R.id.custom_toast_message);
        custom_toast_message.setText(msg);
        final Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        //toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setView(layout);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, time_in_millis);

    }

    public static void toastWarning(Activity context,String msg) {

        LayoutInflater li = context.getLayoutInflater();
        //View layout = li.inflate(R.layout.toast_layout_warning, (ViewGroup) context.findViewById(R.id.custom_toast_layout));
        View layout = li.inflate(R.layout.toast_layout_error, (ViewGroup) context.findViewById(R.id.custom_toast_layout));
        TextView custom_toast_message = layout.findViewById(R.id.custom_toast_message);
        custom_toast_message.setText(msg);
        final Toast toast = new Toast(context);
        //toast.setDuration(Toast.LENGTH_SHORT);
        //toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setView(layout);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 1000);

    }

    public static void toastWarning(Activity context,String msg,int time_in_millis) {

        LayoutInflater li = context.getLayoutInflater();
        View layout = li.inflate(R.layout.toast_layout_warning, (ViewGroup) context.findViewById(R.id.custom_toast_layout));
        TextView custom_toast_message = layout.findViewById(R.id.custom_toast_message);
        custom_toast_message.setText(msg);
        final Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        //toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setView(layout);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, time_in_millis);

    }

    public static void toastSuccess(Activity context,String msg) {

        LayoutInflater li = context.getLayoutInflater();
        View layout = li.inflate(R.layout.toast_layout_success, (ViewGroup) context.findViewById(R.id.custom_toast_layout));
        TextView custom_toast_message = layout.findViewById(R.id.custom_toast_message);
        custom_toast_message.setText(msg);
        final Toast toast = new Toast(context);
        //toast.setDuration(Toast.LENGTH_SHORT);
        //toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setView(layout);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 1000);

    }

    public static void toastSuccess(Activity context,String msg,int time_in_millis) {

        LayoutInflater li = context.getLayoutInflater();
        View layout = li.inflate(R.layout.toast_layout_success, (ViewGroup) context.findViewById(R.id.custom_toast_layout));
        TextView custom_toast_message = layout.findViewById(R.id.custom_toast_message);
        custom_toast_message.setText(msg);
        final Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        //toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setView(layout);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, time_in_millis);

    }
    
    public static  void toastNormal(Activity context,String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static boolean containsIgnoreCase(List<String> list, String soughtFor) {
        for (String current : list) {
            if (current.equalsIgnoreCase(soughtFor)) {
                return true;
            }
        }
        return false;
    }

    public static boolean coreContains(List<String> list, String soughtFor) {
        for (String current : list) {

            String currentNew = current.replaceAll(" ", "");
            String soughtForNew = soughtFor.replaceAll(" ", "");

            if (currentNew.equalsIgnoreCase(soughtForNew)) {
                return true;
            }
        }
        return false;
    }
    
    
}
