# Clash of Clans - Clan lookup

## AWS:
The clash of clans api documentation requires you to register your IP address
to receive your key. This leads to problems (with moble development especially)
because our IP is always changing. To work around this, I created an AWS VPC and
associated an elastic IP. Then by configuring my Lambda function, which runs my api call,
to run through this IP, I can call this Lambda function from anywhere and recieve 
a successful response.
#### Resources:
##### A VPC with:
* Public Subnet
* Private Subnet
* NAT Gateway
* Elastic IP
* 2 Routes (public/private)
* Internet Gateway
##### Lambda
##### API Gateway

#### VPC Configuration:
* _Create a VPC_
* _Create two subnets (private/public)_
* _Create Internet Gateway_
* _Attach igw to VPC to enable communication with the internet._
* _Create public route table and link it to VPC_
* _point new route at igw_
* _Associate route with public subnet_
* _Create NAT gateway_
* _Create an elastic IP_
* _Assign new elastic IP with NAT gateway_
* _Edit default route and set all traffic (0.0.0.0/0) to target our nat instance id_

#### Lambda and API Gateway Configuration:
* _Create a lambda function with the blueprint "microservice-http-endpoint"_
* _Create Role and configure VPC setttings_
* _When you enable VPC, your Lambda function will lose default internet
 access. If you require external internet access for your function,
  ensure that your security group allows outbound connections
   and that your VPC has a NAT gateway._
* _Deploy Api_
* _Call api from android, inputting clanTag input as query parameter_

## App:

#### Setup
* _Clone down app_
* _Open in android studio_
* _Run on emulator (Nexus 6P API 26)_
* _Create user and login_
* _Input clan tag into input on first page (see tags at bottom of README)_
* _ClanTag is passed to service_
* _ClanTag urlEncoded_ 
* _Api call is made through api gateway to AWS Lambda function and returns JSON result_

### App Features

#### Input Validation
* _create new textchangelistener on our editText that creates a new TestWatcher_
* _Inside TextWatcher, validate code on text change_
* _If code matches regex .*[^A-Z^0-9^#].* user will be prompted with a toast,
telling them the correct format
* _---Validation on button for length---_

#### API Call To Get Clan JSON
* _Clan tag input is declared query parameter to AWS API Gateway url_
* _Call made to AWS API Gateway, which calls our AWS Lambda Function_
* _AWS Lambda function makes api call in node.js to Clash of Clans API_
* _AWS Lambda function is sent to public subnet of my AWS VPC_
* _API call now comes from our Elastic IP address in our VPC_
* _JSON is sent back down chain to android_

#### API - On Response
* _On API response, use runOnUiThread function, and inside set our displayed clan views. This
must be done in this function to merge our "API work thread" with our UI(Main) thread.
* _Set picture and resize using Picasso_

#### Menu Items
* _Logout - Logs User out from App_
* _Save - Save a clan to firebase under your registered account_

#### Saved Clans List View
* _Reuse our memberlist recyclerView and layout as our new View in SavedClansListActivity_
* _Create custom saved_clan_list_item.xml view for a new FirebaseClanViewHolder_
* _Pass our custom FirebaseClanViewHolder into our mFirebaseAdapter_
* _Set our RecyclerView to use our newly configured mFirebaseAdapter_
* _When the activity is destroyed, cleanup() is called on the adapter so that it will stop listening for changes in Firebase._

#### NavBar

#### Login
* _Create instance of FirebaseAuth_
* _Validate email and password_
* _Authenticate user with built-in firebase method called signInWithEmailAndPassword()_
* _On complete, if Authenitication is successful, we will send intent to Main Activity from AuthStateListener_

#### Register User

#### Progress Dialogs
* _In CreateAccountActivity, dismissed onComplete in firebase createUserWithEmailAndPassword method_
* _In LoginActivity, dismissed onComplete of firebase signInWithEmailAndPassword method_

#### Firebase Database
* _Array named clans, composed of nodes for each User_ 
* _Each User has a Node using their unique uid key_
* _push saved Clan's into associated User's node_
* _Clan push Id also saved as attribute_

#### Shared Preferences
* _Used to save last user logged in, displays users username next time they visit the login page_

##### ClanTags
* _#G9J8PL8Q_
* _#QGJY8UR_
* _#PO8RVRV9_




