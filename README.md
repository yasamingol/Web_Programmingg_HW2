Exercise Description: 
In this exercise, a simple web service for storing and retrieving group information about products in a class has been created. Another endpoint has been added that, upon receiving a product identifier (id) in the address, delivers the information of that single product to the user.

Implementation:
The implementatoin details are as shown below.


Example Test:
GET loclahost:8080/api/v1/products/PRD_0
(details of a product with the identifier PRD_0)
![PRD_1](https://github.com/yasamingol/Web_Programmingg_HW2/assets/59180210/57ddf775-076d-4836-a68f-05f3b4437602)


GET loclahost:8080/api/v1/products/PRD_1
(details of a product with the identifier PRD_1)
![PRD_0](https://github.com/yasamingol/Web_Programmingg_HW2/assets/59180210/8314aae0-f7f0-4d9f-bc17-d9984aec1bac)


GET loclahost:8080/api/v1/products/PRD_2
(this product is not found, an appropriate 404 error is shown)
![PRD_404](https://github.com/yasamingol/Web_Programmingg_HW2/assets/59180210/09d1701d-0e62-4b21-bf05-89bb48780ceb)


