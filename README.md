Run: discovery-server -> item-service -> order-service -> api-gateway


Using API gateway-
1. access item-service
	
	- curl -d '{"productCode": "PRD001", "productName": "product name", "quantity": 1000, "unitPrice": 2.5}' -H 'Content-Type: 		application/json' POST localhost:8080/items    

    - PUT localhost:8080/items/PRD001 {
			 "productName": "change product name1",
			"quantity": 1100,
			"unitPrice": 2.5}

	- GET localhost:8080/items

	- DELETE localhost:8080/items/PRD001
		

2. access order-service 

	- POST localhost:8080/orders {
     "customerName": "Ratnesh Kumar",
    "shippingAddress": "166 Singapore"}

	- POST localhost:8080/orders/1/items {
        "productCode": "PRD001",
        "quantity": 1}
	- GET localhost:8080/orders/1/items 
	- GET http://localhost:8080/order-service/    (url rewrite filter)
	- GET http://localhost:8080/order-service/1   (url rewrite filter)
	
	
Similarly update order item, delete and get (check end point in controller)

	- GET localhost:8080/orders/1 
	- response: {
    "id": 1,
    "customerName": "customer name",
    "shippingAddress": "abc Singapore",
    "items": [
        {
            "itemId": 2,
            "productCode": "PRD001",
            "productName": "product name1",
            "quantity": 1,
            "unitPrice": 2.5
        }
    ],
    "total": 2.5}