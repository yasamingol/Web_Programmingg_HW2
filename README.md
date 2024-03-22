Exercise Description: 
In this exercise, a simple web service for storing and retrieving group information about products in a class has been created. Another endpoint has been added that, upon receiving a product identifier (id) in the address, delivers the information of that single product to the user.

Implementation:
The implementatoin details are as shown below.
![HW2_1](https://github.com/yasamingol/Web_Programmingg_HW2/assets/59180210/5e8d0e2c-67ed-40c2-8523-004597ce4137)
![HW2_2](https://github.com/yasamingol/Web_Programmingg_HW2/assets/59180210/2ff50e42-8566-4731-a33a-9aa9fd584236)
![HW2_3](https://github.com/yasamingol/Web_Programmingg_HW2/assets/59180210/aac0a6be-07a2-433c-bb61-f78ffffd0c82)
![HW2_4](https://github.com/yasamingol/Web_Programmingg_HW2/assets/59180210/e4779a1f-9e3f-4a6a-bb7b-1e99e8071185)


Example Test:
GET loclahost:8080/api/v1/products/PRD_0
(details of a product with the identifier PRD_0)
![PRD_1](https://github.com/yasamingol/Web_Programmingg_HW2/assets/59180210/57ddf775-076d-4836-a68f-05f3b4437602)


GET loclahost:8080/api/v1/products/PRD_1
(details of a product with the identifier PRD_1)
![PRD_0](https://github.com/yasamingol/Web_Programmingg_HW2/assets/59180210/8314aae0-f7f0-4d9f-bc17-d9984aec1bac)


GET loclahost:8080/api/v1/products/PRD_2
(this product is not found, an appropriate 404 error is shown)
![PRD_404 full](https://github.com/yasamingol/Web_Programmingg_HW2/assets/59180210/1dfc551a-d807-4d32-aa20-0173894ccbad)



