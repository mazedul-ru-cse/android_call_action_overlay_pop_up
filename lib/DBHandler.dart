import 'dart:io';

import 'package:sqflite/sqflite.dart';
import 'package:path_provider/path_provider.dart';

class DBHandler {

  static const dbName = "iCRM.db";
  static const tableName = "follow_up";


  // creating a constant variables for our database.
  // below variable is for our database name.

  // below int is our database version
  static const DB_VERSION = 1;

  // below variable is for our id column.
  static const ID_COL = "id";

  // below variable is for our course name column
  static const NEXT_FOLLOW_UP = "next_follow_up";

  // below variable id for our course duration column.
  static const NOTE = "note";

  // below variable for our course description column.
  static const CALL_STATUS = "call_status";

  // below variable is for our course tracks column.
  static const ASSIGN_TO = "assign_to";
  static const OTHER = "other";

  static final DBHandler instance = DBHandler();

  Future<Database?> get database async{

    return await initDB();
  }

  initDB() async{
    Directory directory = await getApplicationDocumentsDirectory();
   // String path = join(directory.path,dbName);
    String path = "/data/data/com.example.over_lay_pop_up/databases/iCRM.db";
    print("Database path : $path");
    return await openDatabase(path,version: 2);
  }

  Future onCreate(Database db , int version) async{

    db.execute('''
    CREATE TABLE $tableName (
                $ID_COL INTEGER PRIMARY KEY AUTOINCREMENT,
                $NEXT_FOLLOW_UP DATETIME,
                $NOTE  TEXT,
                $CALL_STATUS TEXT,
                $ASSIGN_TO TEXT,
                $OTHER TEXT
                )''');
  }

  Future<List<Map<String,dynamic>>> readData() async{
    Database? db = await instance.database;
    return await db!.query(tableName);
  }

  insert(Map<String,dynamic> row) async{
    Database? db = await instance.database;
    return await db!.insert(tableName, row);
  }

}