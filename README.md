# rest-client
This project describe how rest client communcate with rest-server using RestTemplate-SpringBoot
Fork this project from mya github
How to run ?
1. import into intellij Idea
2. rest-server already run in port 8080
3. run this rest-client in port 8081

try to test application -> http://localhost:8081/list/product/

Let's understand the flow
   flow when show the list product
   - user hit this url http://localhost:8081/list/product/
   - rest-client send request to rest-server
   - when product exist
   - mapping product with ui(product-list.html)
   flow when save product
   - show list product
   - click button add
   - show form to input product
   - mapping action from form
   - convert product into json
   - send value to rest-server
   - if success, show the list product already added
   flow when delete
   - show list product
   - select row , you want to delete
   - send request to rest-server
   - if success redirect into list product page
   flow when update
   - show list product
   - select row, you want update
   - mapping to edit page
   - submit, and send to rest-server
   - if success redirect to list product page
