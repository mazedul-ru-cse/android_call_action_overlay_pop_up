import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:over_lay_pop_up/OverlayPopUP.dart';

void main(){

  runApp(const ExampleApp());
}


@pragma("vm:entry-point")
void overlayMain() {
  WidgetsFlutterBinding.ensureInitialized();
  runApp(const PopUpDialog());
}

class ExampleApp extends StatefulWidget {
  const ExampleApp({Key? key}) : super(key: key);

  @override
  State<ExampleApp> createState() => _ExampleAppState();
}

class _ExampleAppState extends State<ExampleApp> {

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
  }
  @override
  Widget build(BuildContext context) {
   // showPopUp();
    return MaterialApp(
        home: Scaffold(
            body: HomePage()
        )
    );
  }

  Future<void> showPopUp() async {

    await OverlayPopUP.requestPermission();

    await OverlayPopUP.showOverlay(
      enableDrag: true,
      overlayTitle: "X-SLAYER",
      overlayContent: 'Overlay Enabled',
      flag: OverlayFlag.defaultFlag,
      alignment: OverlayAlignment.bottomCenter,
      visibility: NotificationVisibility.visibilityPublic,
      positionGravity: PositionGravity.left,
      width: 400,
      height: 200,
    );
  }
}


class PopUpDialog extends StatefulWidget {
  const PopUpDialog({Key? key}) : super(key: key);

  @override
  State<PopUpDialog> createState() => _PopUpDialogState();
}

class _PopUpDialogState extends State<PopUpDialog> {
  @override
  Widget build(BuildContext context) {

    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Container(
        width:200,
        height: 200,
        decoration: BoxDecoration(
          color: Colors.blue,
        ),

        child: TextButton(onPressed: () {
          OverlayPopUP.closeOverlay();

        }, child:const Text("Close",style: TextStyle(color: Colors.white),),

        ),

      ),
    );


  }
}

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  void initState() {
    super.initState();
    log("Started listening");
    OverlayPopUP.overlayListener.listen((event) {
      log("$event");
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Plugin example app'),
      ),
      body: Center(
        child: Column(
          children: [
            TextButton(
              onPressed: () async {
                final status = await OverlayPopUP.isPermissionGranted();
                log("Is Permission Granted: $status");
              },
              child: const Text("Check Permission"),
            ),
            const SizedBox(height: 10.0),
            TextButton(
              onPressed: () async {
                final bool? res =
                await OverlayPopUP.requestPermission();
                log("status: $res");
              },
              child: const Text("Request Permission"),
            ),
            const SizedBox(height: 10.0),
            TextButton(
              onPressed: () async {
                if (await OverlayPopUP.isActive()) return;
                await OverlayPopUP.showOverlay(
                  enableDrag: true,
                  overlayTitle: "X-SLAYER",
                  overlayContent: 'Overlay Enabled',
                  flag: OverlayFlag.defaultFlag,
                  alignment: OverlayAlignment.centerLeft,
                  visibility: NotificationVisibility.visibilityPublic,
                  positionGravity: PositionGravity.left,
                  height: 200,
                  width: 200,
                );
              },
              child: const Text("Show Overlay"),
            ),
            const SizedBox(height: 10.0),
            TextButton(
              onPressed: () async {
                final status = await OverlayPopUP.isActive();
                log("Is Active?: $status");
              },
              child: const Text("Is Active?"),
            ),
            const SizedBox(height: 10.0),
            TextButton(
              onPressed: () async {
                await OverlayPopUP.shareData('update');
              },
              child: const Text("Update Overlay"),
            ),
            const SizedBox(height: 10.0),
            TextButton(
              onPressed: () {
                log('Try to close');
                OverlayPopUP.closeOverlay()
                    .then((value) => log('STOPPED: alue: $value'));
              },
              child: const Text("Close Overlay"),
            ),
          ],
        ),
      ),
    );
  }
}
