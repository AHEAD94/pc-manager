package com.example.pcmanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestReportClicked extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_report_clicked);

        Intent intent = getIntent();

        button = findViewById(R.id.bt_report_generate);
        SetListener();
    }

    public void SetListener() {
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                uploadExcel();
            }
        });
    }

    private  void uploadExcel() {

        Intent intent = getIntent();

        HSSFWorkbook workbook = new HSSFWorkbook();

        Sheet sheet = workbook.createSheet();

        Row row;
        int row_now = -1;
        Cell cell;
        int cell_now = -1;

        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 30);
        font.setBold(true);

        /******************* title *******************/
        for (int r = 0; r < 3; r++) {
            row = sheet.createRow(r);
            row_now++;
            for (int c = 0; c < 9; c++) {
                cell = row.createCell(c);
                cell.setCellValue("검사보고서");
            }
        }
        sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 8));
        /******************* title *******************/


        /******************* report info *******************/
        row = sheet.createRow(++row_now);

        cell = row.createCell(++cell_now);
        cell.setCellValue("발행번호");
        for (int c = cell_now+1; c < cell_now+3; c++) {
            cell = row.createCell(c);
            cell.setCellValue("No.0");
        }
        sheet.addMergedRegion(new CellRangeAddress(row_now, row_now, ++cell_now, ++cell_now));

        cell = row.createCell(++cell_now);
        cell.setCellValue("발행인");
        for (int c = cell_now+1; c < cell_now+3; c++) {
            cell = row.createCell(c);
            cell.setCellValue("null");
        }
        sheet.addMergedRegion(new CellRangeAddress(row_now, row_now, ++cell_now, ++cell_now));

        cell = row.createCell(++cell_now);
        cell.setCellValue("발행일자");
        for (int c = cell_now+1; c < cell_now+3; c++) {
            cell = row.createCell(c);
            cell.setCellValue("null");
        }
        sheet.addMergedRegion(new CellRangeAddress(row_now, row_now, ++cell_now, ++cell_now));

        cell_now = -1;
        /******************* report info *******************/


        /******************* module info *******************/
        row = sheet.createRow(++row_now);

        cell = row.createCell(++cell_now);
        cell.setCellValue("부재번호");
        for (int c = cell_now+1; c < cell_now+3; c++) {
            cell = row.createCell(c);
            cell.setCellValue(intent.getStringExtra("moduleNo"));
        }
        sheet.addMergedRegion(new CellRangeAddress(row_now, row_now, ++cell_now, ++cell_now));

        cell = row.createCell(++cell_now);
        cell.setCellValue("생산일자");
        for (int c = cell_now+1; c < cell_now+3; c++) {
            cell = row.createCell(c);
            cell.setCellValue(intent.getStringExtra("finishing"));
        }
        sheet.addMergedRegion(new CellRangeAddress(row_now, row_now, ++cell_now, ++cell_now));

        cell = row.createCell(++cell_now);
        cell.setCellValue("생산자");
        for (int c = cell_now+1; c < cell_now+3; c++) {
            cell = row.createCell(c);
            cell.setCellValue(intent.getStringExtra("producer"));
        }
        sheet.addMergedRegion(new CellRangeAddress(row_now, row_now, ++cell_now, ++cell_now));

        cell_now = -1;
        /******************* module info *******************/


        /******************** items ********************/
        row = sheet.createRow(++row_now);

        cell = row.createCell(++cell_now);
        cell.setCellValue("검사항목");
        for (int c = cell_now+1; c < cell_now+3; c++) {
            cell = row.createCell(c);
            cell.setCellValue("시험 및 검사내용 (검사기준)");
        }
        cell_now += 3;
        cell = row.createCell(++cell_now);
        cell.setCellValue("X1");
        cell = row.createCell(++cell_now);
        cell.setCellValue("X2");
        cell = row.createCell(++cell_now);
        cell.setCellValue("X3");
        cell = row.createCell(++cell_now);
        cell.setCellValue("판정");
        cell = row.createCell(++cell_now);
        cell.setCellValue("검사자");

        cell_now = -1;

        row = sheet.createRow(++row_now);

        cell = row.createCell(++cell_now);
        cell.setCellValue("검사항목");
        for (int c = cell_now+1; c < cell_now+3; c++) {
            cell = row.createCell(c);
            cell.setCellValue("시험 및 검사내용\n(검사기)");
        }
        cell_now += 3;
        cell = row.createCell(++cell_now);
        cell.setCellValue("X4");
        cell = row.createCell(++cell_now);
        cell.setCellValue("X5");
        cell = row.createCell(++cell_now);
        cell.setCellValue("X6");
        cell = row.createCell(++cell_now);
        cell.setCellValue("판정");
        cell = row.createCell(++cell_now);
        cell.setCellValue("검사자");

        cell_now = -1;

        sheet.addMergedRegion(new CellRangeAddress(row_now-1, row_now, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(row_now-1, row_now, 1, 3));
        sheet.addMergedRegion(new CellRangeAddress(row_now-1, row_now, 7, 7));
        sheet.addMergedRegion(new CellRangeAddress(row_now-1, row_now, 8, 8));
        /******************** items ********************/


        /******************** test list ********************/
        row = sheet.createRow(++row_now);

        cell = row.createCell(++cell_now);
        cell.setCellValue("null");
        for (int c = cell_now+1; c < cell_now+3; c++) {
            cell = row.createCell(c);
            cell.setCellValue("null");
        }
        cell_now += 3;
        cell = row.createCell(++cell_now);
        cell.setCellValue("null");
        cell = row.createCell(++cell_now);
        cell.setCellValue("null");
        cell = row.createCell(++cell_now);
        cell.setCellValue("null");
        cell = row.createCell(++cell_now);
        cell.setCellValue("null");
        cell = row.createCell(++cell_now);
        cell.setCellValue("null");

        cell_now = -1;

        row = sheet.createRow(++row_now);

        cell = row.createCell(++cell_now);
        cell.setCellValue("null");
        for (int c = cell_now+1; c < cell_now+3; c++) {
            cell = row.createCell(c);
            cell.setCellValue("null");
        }
        cell_now += 3;
        cell = row.createCell(++cell_now);
        cell.setCellValue("null");
        cell = row.createCell(++cell_now);
        cell.setCellValue("null");
        cell = row.createCell(++cell_now);
        cell.setCellValue("null");
        cell = row.createCell(++cell_now);
        cell.setCellValue("null");
        cell = row.createCell(++cell_now);
        cell.setCellValue("null");

        cell_now = -1;

        sheet.addMergedRegion(new CellRangeAddress(row_now-1, row_now, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(row_now-1, row_now, 1, 3));
        sheet.addMergedRegion(new CellRangeAddress(row_now-1, row_now, 7, 7));
        sheet.addMergedRegion(new CellRangeAddress(row_now-1, row_now, 8, 8));
        /******************** test list ********************/

        //style.setVerticalAlignment(VerticalAlignment.forInt((short)1));
        //FirebaseStorage storage = FirebaseStorage.getInstance();

        String filename = "[TestReport].xls";

        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File xls = new File(getExternalFilesDir(null), filename);

            try {
                FileOutputStream os = new FileOutputStream(xls);
                workbook.write(os);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(), xls.getAbsolutePath() + "에 저장되었습니다.", Toast.LENGTH_LONG).show();

            //Uri path = Uri.fromFile(xls);
            Uri path = FileProvider.getUriForFile(getBaseContext(), "com.example.pcmanager.fileprovider", xls);
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("application/excel");
            shareIntent.putExtra(Intent.EXTRA_STREAM, path);
            startActivity(Intent.createChooser(shareIntent, "보고서 내보내기"));
        }
        else {
            Log.d("EEEㄸㄸㄸㄸㄸ", "External Storage is not ready");
        }



        //StorageReference storageRef = storage.getReferenceFromUrl("https://console.firebase.google.com/u/0/project/pc-quality-manager/storage/pc-quality-manager.appspot.com/files").child("test_reports");
        //storageRef.putFile(456);



    }
}
