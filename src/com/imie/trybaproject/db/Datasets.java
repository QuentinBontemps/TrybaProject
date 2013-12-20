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
		Station stationProduction = new Station();
		
		
		// init station data
		stationDecoupe.setName("Station Découpe");
		stationDecoupe.setOrder(1);
		stationDecoupe.setTampon(tamponDecoupe);
		stationDecoupe.setVisible(true);

		stationFaconnage.setName("Station Faconnage");
		stationFaconnage.setOrder(2);
		stationFaconnage.setTampon(tamponFaconnage);
		stationFaconnage.setVisible(true);

		stationPeinture.setName("Station Peinture");
		stationPeinture.setOrder(3);
		stationPeinture.setTampon(tamponPeinture);
		stationPeinture.setVisible(true);

		stationAssemblage.setName("Station Assemblage");
		stationAssemblage.setOrder(4);
		stationAssemblage.setTampon(tamponAssemblage);
		stationAssemblage.setVisible(true);
			
		stationProduction.setName("Station non utilisée");
		stationProduction.setOrder(5);
		stationProduction.setTampon(tamponProduction);
		stationProduction.setVisible(false);
		
		
		// Insertion
		stationAdapter.insert(stationDecoupe);
		stationAdapter.insert(stationFaconnage);
		stationAdapter.insert(stationPeinture);
		stationAdapter.insert(stationAssemblage);
		stationAdapter.insert(stationProduction);
	}
	
	public static void initTampon(SQLiteDatabase db)
	{
		TamponAdapter tamponAdapter = new TamponAdapter();
		tamponAdapter.setDatabase(db);
		
		
		// Init tampon data
		tamponDecoupe = new Tampon("Stock",50);
		tamponFaconnage = new Tampon("Tampon Faconnage",5);
		tamponPeinture = new Tampon("Tampon Peinture",5);
		tamponAssemblage = new Tampon("Tampon Assemblage",5);
		tamponProduction = new Tampon("Production",1000);
		

		
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
