package com.google.code.withandro;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class Locater implements LocationListener {

	// �����o
	LocationManager locMgr;
	ArrayList<Location> locs;
	Location befLoc;
	double distance; // meter

	Location oneLoc;
	Location twoLoc;
	
	int acccount;
	
	Nisedrod activity;
	

	// TRK�o�͗p
	Date date;
	SimpleDateFormat dateFmt;
	
	
	private static Locater self;
	
	// �R���X�g���N�^
	private Locater() {
		SDLog.put("", true, SDLog.SDFILE);
		
		date = new Date();
		dateFmt = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
	}
	
	public static Locater GetInstance() {
			if (self == null) {
				self = new Locater();
			}
			
			return self;
	}
	
	// ������
	public void Open(Activity act) {
		SDLog.put("", true, SDLog.SDFILE);

		if (act == null) {
			SDLog.put("Activity is null.", true, SDLog.SDFILE);
			return;
		}
		
		//if (activity == null) {
			activity = (Nisedrod)act;
		//}
		
		if (locMgr != null) {
			SDLog.put("LocationManager already opened.", true, SDLog.SDFILE);
			return;
		}
		
		locMgr = (LocationManager)act.getSystemService(Context.LOCATION_SERVICE);
		if (locMgr == null) {
			SDLog.put("LocationManager not get", true, SDLog.SDFILE);
			return;
		}

		oneLoc = null;
		twoLoc = null;
		acccount = 0;
		distance = 0;
		
		if (locs == null) {
			locs = new ArrayList<Location>();
		} else {
			locs.clear();
		}
		
		SDLog.put(CreateTrkHeader(), false, SDLog.GPSTRKFILE);
		locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
	}
	
	// �f�X�g���N�^
	public void Close() {
		SDLog.put("", true, SDLog.SDFILE);
		if (locMgr == null) {
			return;
		}

		locMgr.removeUpdates(this);
		locMgr = null;

		//OutputTrk();
		locs.clear();
		oneLoc = null;
		twoLoc = null;
		acccount = 0;
		distance = 0;		
	}
	
	
	public void onLocationChanged(Location loc) {
		//SDLog.put("", true, SDLog.SDFILE);
		
		//locs.add(arg0);
		CalcDistance(loc);
	}

	public void onProviderDisabled(String arg0) {
		SDLog.put("", true, SDLog.SDFILE);

	}

	public void onProviderEnabled(String arg0) {
		SDLog.put("", true, SDLog.SDFILE);

	}

	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		SDLog.put("", true, SDLog.SDFILE);

	}


	// �������v�Z
	public void CalcDistance(Location loc) {
		//SDLog.put("", true, SDLog.SDFILE);
		//Location loc = locs.get(locs.size()-1);
		SDLog.put(CreateOneTrk(loc), false, SDLog.GPSTRKFILE);
		activity.UpdateDisplay(loc, distance);
		
		if (!loc.hasAccuracy()) {
			return;
		}
		
		// ����
		if (oneLoc == null) {
			oneLoc = loc;
			return;
		}
		twoLoc = loc;
		if (twoLoc.getAccuracy() > 15) {
			acccount--;
		}
		
		acccount++;
		if (acccount > 2) {
			acccount--;
			
			float dist = oneLoc.distanceTo(twoLoc);
			// �K��
			if (dist >= 1.0) {
				distance += dist;
				activity.PlaySoundOverDistance(distance);
			}
			//SDLog.put("total["+distance+"], dist["+dist+"], " + loc.toString(), false, SDLog.GPSLOGFILE);			
		}
				
		// �X�V
		oneLoc = twoLoc;
	
		/*
		Location lastLoc = GetLastValidLocation();
		if (lastLoc == null) {
			return;
		}

		double dist = (double)befLoc.distanceTo(lastLoc);
		befLoc = lastLoc;
		*/
	}

	
	// �Ō�̃��P�[�V�������擾
	public Location GetLastValidLocation() {
		SDLog.put("", true, SDLog.SDFILE);
		Location ret = null;
	
		// �O����H
		// ��납��A�O��̂��������H�H
		// �H�H�H
		// ���P�[�V�������X�g����납���
		for (int i = locs.size()-1; i > 0; i--) {
			Location loc = locs.get(i);

			// �����x�������Ă��Ȃ��ꍇ�́A�O��
			if (!loc.hasAccuracy()) {
				continue;
			}
			
			// �ʂ̏����`
		
			ret = loc;
			if (ret != null) {
				break;
			}
		}
		
		return ret;
	}


	public String CreateOneTrk(Location loc) {
		if (loc == null) {
			return null;
		}

		date.setTime(loc.getTime());

		/*
		H  LATITUDE    LONGITUDE    DATE      TIME     ALT
		T  N35.7245560 E139.7571230 09-NOV-10 10:05:15 36
		T  N35.7245560 E139.7571230 09-NOV-10 10:05:25 36
		T  N35.7245350 E139.7571020 09-NOV-10 10:05:35 38
		T  N35.7242990 E139.7566080 09-NOV-10 10:05:45 38
		*/

		String trk = String.format("T  N%.07f E%.07f %s %03d %3.1f %3d %8d",
				loc.getLatitude(),
				loc.getLongitude(),
				dateFmt.format(date),
				(int)loc.getAltitude(),
				loc.getSpeed(),
				(int)loc.getBearing(),
				(int)loc.getAccuracy()
		);
		
		return trk;
	}
	
	public String CreateTrkHeader() {
		String header = 
			"H  SOFTWARE NAME & VERSION\n" +
			"I  PCX5 2.09\n" +
			"\n" +
			"H  COORDINATE SYSTEM\n" +
			"U  LAT LON DEG\n" +
			"\n" +
			"H  R DATUM                IDX DA            DF            DX            DY            DZ\n" +
			"M  G WGS 84               121 +0.000000e+00 +0.000000e+00 +0.000000e+00 +0.000000e+00 +0.000000e+00\n" +
			"\n" +
			"H ACTIVE LOG\n" +
			"\n" +
			"H  LATITUDE    LONGITUDE    DATE      TIME     ALT\n";
		return header;
	}
	
}
