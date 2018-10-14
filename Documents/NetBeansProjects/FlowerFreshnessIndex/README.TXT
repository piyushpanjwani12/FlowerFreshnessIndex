-> The technology used is Opencv with java

-> The input images are given.(flower1.png, flower2.png, flower3.png, flower4.png, flower5.png) For calculating the index, we need references. The jar file initially requires two reference images 1.) Image of a fresh flower(let "flower1.png") 2.) Image of the wilted flower(let "flower4.png") Then using these images, a reference is made. For calculating the freshness, a new image is put in the software, and its freshness index is calculated and presented.

-> The output is generated in the form of a slider. Also a freshness percent is reported.

->The source code is present in the src folder. It consists of the complete code and the opencv library.

-> Instructions to deploy and run
1. Paste the "opencv_java2411.dll" file in the system32 folder of your computer.("C:\Windows\System32")
2. Now run the FlowerFreshnessIndex.jar file.
3. Now enter the name of the fresh flower image and wilted flower image.
click submit.
4. Now enter a random flower image(between fresh and wilted flower) to check the freshness index.
5. You can test the code using the (flower1.png, flower2.png, flower3.png, flower4.png, flower5.png) images given in the repository.
6. The dll file mentioned above is important to be uploaded in the system32 folder.
