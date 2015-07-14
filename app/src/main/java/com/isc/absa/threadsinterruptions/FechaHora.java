package com.isc.absa.threadsinterruptions;

import android.annotation.SuppressLint;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class FechaHora {
	public static String getFyH() {
		Date date = new Date();
		DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return ("" + hourdateFormat.format(date));
	}

	public static String getH() {
		Date date = new Date();
		DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss");
		return ("" + hourdateFormat.format(date));
	}

	public static String getF() {
		Date date = new Date();
		DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return ("" + hourdateFormat.format(date));
	}

}