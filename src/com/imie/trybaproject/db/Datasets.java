package com.imie.trybaproject.db;

import android.database.sqlite.SQLiteDatabase;

import com.imie.trybaproject.model.Station;
import com.imie.trybaproject.model.Tampon;
import com.imie.trybaproject.model.User;
import com.imie.trybaproject.model.UserType;

public final class Datasets {

	// Variables
	static Tampon tamponDecoupe;
	static Tampon tamponFaconnage;
	static Tampon tamponPeinture;
	static Tampon tamponAssemblage;
	static Tampon tamponProduction;
	
	
	public static void initData(SQLiteDatabase db)
	{
		initUsers(db);
		initTampon(db);
		initStation(db);
		
		db.close();
	}
	
	public static void initStation(SQLiteDatabase db)
	{
		
		StationAdapter stationAdapter = new StationAdapter();
		stationAdapter.setDatabase(db);

		
		
		Station stationDecoupe = new Station();
		Station stationFaconnage = new Station();
		Station stationPeinture = new Station();
		Station stationAssemblage = new Station();
		
		
		// init station data
		stationDecoupe.setName("StationDecoupe");
		stationDecoupe.setOrder(1);
		stationDecoupe.setTampon(tamponDecoupe);
		stationDecoupe.setVisible(true);

		stationFaconnage.setName("StationFaconnage");
		stationFaconnage.setOrder(2);
		stationFaconnage.setTampon(tamponFaconnage);
		stationFaconnage.setVisible(true);

		stationPeinture.setName("StationPeinture");
		stationPeinture.setOrder(3);
		stationPeinture.setTampon(tamponPeinture);
		stationPeinture.setVisible(true);

		stationAssemblage.setName("StationAssemblage");
		stationAssemblage.setOrder(4);
		stationAssemblage.setTampon(tamponAssemblage);
		stationAssemblage.setVisible(true);
				
		
		// Insertion
		stationAdapter.insert(stationDecoupe);
		stationAdapter.insert(stationFaconnage);
		stationAdapter.insert(stationPeinture);
		stationAdapter.insert(stationAssemblage);
	}
	
	public static void initTampon(SQLiteDatabase db)
	{
		TamponAdapter tamponAdapter = new TamponAdapter();
		tamponAdapter.setDatabase(db);
		
		
		// Init tampon data
		tamponDecoupe = new Tampon("DecoupeTampon",50);
		tamponFaconnage = new Tampon("FaconnageTampon",5);
		tamponPeinture = new Tampon("PeintureTampon",5);
		tamponAssemblage = new Tampon("AssemblageTampon",5);
		tamponProduction = new Tampon("ProductionTampon",1000);
		

		
		// Insertion
		tamponProduction.setId(tamponAdapter.insert(tamponProduction));
		tamponDecoupe.setId(tamponAdapter.insert(tamponDecoupe));
		tamponFaconnage.setId(tamponAdapter.insert(tamponFaconnage));
		tamponPeinture.setId(tamponAdapter.insert(tamponPeinture));
		tamponAssemblage.setId(tamponAdapter.insert(tamponAssemblage));
		
	}
	
	public static void initUsers(SQLiteDatabase db)
	{
		// Add user in DB pour test
		UserAdapter userAdapter = new UserAdapter();
		userAdapter.setDatabase(db);
		userAdapter.insert(new User("momo","yo","Quentin","Bontemps",
				UserType.ADMINISTRATOR.name())); 
		userAdapter.insert(new User("toto","yo","Antonin","Auffray",
				UserType.OPERATOR.name())); 
	}
	
	
}
