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
* _clone down app_
* _input clan tag into input on first page (#QGJY8UR)_
* _clan tag is urlEncoded and passed to service_
* _api call is made through api gateway to lambda function and returns json_

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
* _Pass our custom FirebaseClanViewHolder into a FirebaseRecyclerAdapter_




