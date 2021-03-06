 I got an opportunity for an internship through reference. 

 I was given two days time to create an CRUD App from Scratch to be selected for the internship. 

# Start of Task



An overview of the App that I want you to build to show the knowledge you have in this area


This will be a CRUD (Create, inseRt, Update, Delete) app. The app allows the user to search a database of Amazon Sellers, by name, revenue, number of employees or location.


1. Create a database with the following fields:
   1. Company
     1. Company ID
     2. Company name (required)
     3. Location
     4. SellerID 
     5. URL (Required)
     6. Revenue (Required)
     7. Product categories (Required)
     8. Number of employees (If Available)
     9. Address (Required)
     10. Decision makers (connected to Person table)
     11. Company LinkedIn URL (If Available)
     12. Company Amazon Seller page  (Required)
     13. Logo (If Available)
   2. Person (belongs to a company)
     1. Person ID
     2. Full name (required)
     3. Title
     4. LinkedIn URL (required)
     5. Email address (required)
     6. Job function (optional)
2. Allow for a user to create new Company and add the fields as above
3. Allow for user to edit company
4. Allow for user to delete company
5. Allow for user to search for company and display relevant field
6. Allow for user to create person attached to a company, delete and edit Person  
  
  

# End of Task


The following is how I have implemented the same :



# Company Structure

![alt text](https://sarveshwaran1678.github.io/CRUD-Android-Application/Screenshots/1.jpeg)
![alt text](https://sarveshwaran1678.github.io/CRUD-Android-Application/Screenshots/2.jpeg)

# Adding Details To Company

![alt text](https://sarveshwaran1678.github.io/CRUD-Android-Application/Screenshots/3.jpeg)
![alt text](https://sarveshwaran1678.github.io/CRUD-Android-Application/Screenshots/4.jpeg)
![alt text](https://sarveshwaran1678.github.io/CRUD-Android-Application/Screenshots/5.jpeg)
![alt text](https://sarveshwaran1678.github.io/CRUD-Android-Application/Screenshots/6.jpeg)

# Deleting Details 

![alt text](https://sarveshwaran1678.github.io/CRUD-Android-Application/Screenshots/16.jpeg)
![alt text](https://sarveshwaran1678.github.io/CRUD-Android-Application/Screenshots/17.jpeg)

# Making Changes And Updating

![alt text](https://sarveshwaran1678.github.io/CRUD-Android-Application/Screenshots/19.jpeg)
![alt text](https://sarveshwaran1678.github.io/CRUD-Android-Application/Screenshots/20.jpeg)
![alt text](https://sarveshwaran1678.github.io/CRUD-Android-Application/Screenshots/21.jpeg)

> I deleted the id = 1 before updating it. So , I added the same data and updated it later on with id = 2.
> That's the reason the id changed to 2.


# Person Structure

## Dynamic Company ID Generation

![alt text](https://sarveshwaran1678.github.io/CRUD-Android-Application/Screenshots/7.jpeg)
![alt text](https://sarveshwaran1678.github.io/CRUD-Android-Application/Screenshots/8.jpeg)

# Adding Details To Person

![alt text](https://sarveshwaran1678.github.io/CRUD-Android-Application/Screenshots/9.jpeg)
![alt text](https://sarveshwaran1678.github.io/CRUD-Android-Application/Screenshots/10.jpeg)

# Making Changes And Updating

![alt text](https://sarveshwaran1678.github.io/CRUD-Android-Application/Screenshots/12.jpeg)
![alt text](https://sarveshwaran1678.github.io/CRUD-Android-Application/Screenshots/13.jpeg)

# Deleting Details 

![alt text](https://sarveshwaran1678.github.io/CRUD-Android-Application/Screenshots/14.jpeg)
![alt text](https://sarveshwaran1678.github.io/CRUD-Android-Application/Screenshots/15.jpeg)

# Validation For Fields That Are Required

![alt text](https://sarveshwaran1678.github.io/CRUD-Android-Application/Screenshots/11.jpeg)


# [ To Try Out The App Click Here ! ](https://drive.google.com/file/d/1fEKrK43f-gY0zIOeOd3tSqDYOYuXh7DE/view?usp=sharing)


