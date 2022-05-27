import 'package:flutter/material.dart';
import 'dart:async';
import 'package:http/http.dart' as http;
import 'dart:convert';

void main() => runApp(new MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: 'Flutter Demo',
      theme: new ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: new MyHomePage(title: 'Users'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => new _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  Future<List<User>> _getUsers() async {
    var data = await http.get(Uri.parse("https://randomuser.me/api/?results=10"));
    var jsonData = json.decode(data.body);

    List<User> users = [];
    print(">>>>>>>>>>");
    print(jsonData['results']);
    for (var u in jsonData['results']) {
      String fullName = "${u['name']['title']} ${u['name']['last']} ${u['name']['first']}";
      String id = "${u['id']['name'] } ${u['id']['value']}";
      String address = "${u['location']['street']['number']} ${u['location']['street']['name']} ${u['location']['city']}";
      User user = User(id, fullName, u["email"], u["phone"], u["picture"]["large"], address);

      users.add(user);
    }

    print(users.length);

    return users;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: new AppBar(
        title: new Text(widget.title),
      ),
      body: Container(
        child: FutureBuilder(
          future: _getUsers(),
          builder: (BuildContext context, AsyncSnapshot snapshot) {
            print(snapshot.data);
            if (snapshot.data == null) {
              return Container(child: Center(child: Text("Loading...")));
            } else {
              return ListView.builder(
                itemCount: snapshot.data.length,
                itemBuilder: (BuildContext context, int index) {
                  return Card(
                      child: ListTile(
                        leading: CircleAvatar(
                            backgroundImage: NetworkImage(
                                snapshot.data[index].image)),
                        //snapshot.data[index].picture
                        //): ,),
                        //Colors.lightBlueAccent
                        //backgroundImage: NetworkImage(
                        //snapshot.data[index].picture
                        //),
                        //),
                        title: Text(snapshot.data[index].name),
                        subtitle: Text(snapshot.data[index].email),
                        onTap: () {
                          Navigator.push(
                              context,
                              new MaterialPageRoute(
                                  builder: (context) =>
                                      DetailPage(snapshot.data[index])));
                        },
                      ));
                },
              );
            }
          },
        ),
      ),
    );
  }
}

class DetailPage extends StatelessWidget {
  final User user;

  DetailPage(this.user);
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          user.name,
        ),
      ),
      body: Column(
        children: [
          Stack(
              clipBehavior: Clip.none,
              alignment: Alignment.bottomCenter,
              children: [
                Positioned(
                    child: CircleAvatar(
                    radius: 80,
                    backgroundColor: Colors.black,
                    child: CircleAvatar(
                      radius: 75,
                      backgroundImage: NetworkImage(
                          user.image),
                    )),

                )
              ]),
          SizedBox(
            height: 45,
          ),
          ListTile(
            title: Center(child: Text(user.name))
          ),
          ListTile(
            title: Text("Phone : ${user.phone}"),
          ),
          ListTile(
            title: Text("Email : ${user.email}"),
          ),
          ListTile(
            title: Text("Adresse : ${user.address}"),
          ),

        ],
      ),
    );
  }
}

class User {
  final String id;
  final String name;
  final String email;
  final String phone;
  final String image;
  final String address;

  User(this.id, this.name, this.email, this.phone, this.image, this.address);
}
