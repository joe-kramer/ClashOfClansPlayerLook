# Clash of Clans - Clan lookup

### AWS:
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

### App Setup
* _clone down app_
* _input clan tag into input on first page (#PO8RVRV9)_
* _clan tag is urlEncoded and passed to service_
* _api call is made through api gateway to lambda function and returns json_
