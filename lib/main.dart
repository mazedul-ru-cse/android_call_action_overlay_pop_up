import 'package:flutter/material.dart';
import 'package:over_lay_pop_up/DBHandler.dart';

void main(){

  runApp(const App());
}

class App extends StatefulWidget {
  const App({Key? key}) : super(key: key);

  @override
  State<App> createState() => _AppState();
}

class _AppState extends State<App> {

  String? controller;
  @override
  initState(){
    // TODO: implement initState
    super.initState();

    insert();

  }
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Scaffold(
          body: Center(child: Text(controller.toString())),
    ));
  }

  Future<void> insert() async {

    var read = await DBHandler.instance.readData();

    print(read);
    setState(() {
      controller = read.toString();
    });
    //controller.text = read.toString();

  }
}



