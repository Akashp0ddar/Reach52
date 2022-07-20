# Reach52
In this project what I have done is take the details of patient details like firstname,lastname,image,etc and also the details of Doctor and show them in recyclerView.
All the details were show in a recyclerView and also the details were show from the local database. I have describe the working of this project in few points below

all the process is divided into four parts
Create,
Read,
Update,
Delete.

First when you open the app you will see the splash screen

after the login screen you will see a login screen where you have enter you email or password. it is just a dummy screen only thing you have to do is enter email and password and hit login and you will navigate to next screen

Create :- For creating the when you navigate to screen after login you will a empty screen with a plus( + ) button on clicking on that button you will navigate to new screen 
          where you have to enter the details of the patient as well as doctor and also click on big plus icon to add the picture of patient afte filling out all the details 
          thing you have to do is click on Add button to store all the details in database and you will navigate to previous screen
          
Read :- When you click on the add button you will navigate to previous screen. Where you will see all the details entered by you. All these details were shown directly from the 
        database for the database. I've use the Room database. I know you told me to use realm but I haven't use realm database before. also I confirm from the person that can I
        Room database, she said to yes you can use Room database
        
Update :- So you read all the data but what if you entered wrong data by mistake, you don't need to worry about it only thing you have to do is click on the paticular data and you 
          will navigate to a update screen where you will the previous details entered by you also you can change those details after updating that details, just click on update
          and again you will navigate to previous screen and see the updated details
          
Delete :- For deleting data there are two ways
          1. Paticular data
          2. Entire data
          
          1.Paticular data :- For deleting a paticular data you just need to click on paticular data and you will navigate to updation screen, there you will see a delete button 
                              by clicking on that button you can delete paticular data
                           
          2.Entire data :- For deleting the entire data you will see a dustbin Icon on top right corner on HomeScreen(where you see all your data) by clicking on it you can delete all the data 
          
          
Android Studio :- Android Studio Chipmunk | 2021.2.1


programming language :- Kotlin


JDK version :- JDK 11 
