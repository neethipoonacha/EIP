1. How many layers, 

   The number of layers depends on how well the training data is fit without overfitting .

   The basic priniciple is to add as many layers as long as we are not overfitting the data . If the loss does not improve anymore then stop adding more layers , instead try adding regularisation, max pooling ,batch normalisation , dropout or improve learning rate 

2. MaxPooling, 

   Max pooling is a process of downsampling the input map by either reducing the dimensions and/or allowing assumptions to be made about certain features in the input image .

   1 . Maxpooling reduces the computational cost by reducing the number of parameters to learn 

   2 . Provides translational invariance (images appear exactly same but positioned differently)

   maxpooling is generally done using a 2*2 filter with stride 2 and wont overlap regions 

3. 1x1 Convolutions, 

   A 1*1 filter maps in input pixel with all its channels to the output pixel. It is used to reduce the depth of the channel. Often used at the last layer when we know the output resolution size 

   In Deep convolutional neural networks number of filters often increases the depth of network .This can dramatically increase the number of parameters and computational efficiency required .To address this problem 1*1 can be used to dimensionality reduction,decreasing the number of feature whilst retaining the salient features .  

   It can be used to provide a linear projection of a feature map and also can be used to create more feature maps of the input image

4. 3x3 Convolutions, 

   A popular choice. In image processing. It is used for blurring, sharpening, embossing, edge detection, and more.

   Most of the useful features in an image are usually local , hence it is better to take few local pixels at a time to apply convolutions

   Most of these useful features may be found in more than one place in an image. So, it makes sense to slide a single kernel all over the image in the hope of extracting that feature in different parts of the image using the same kernel.

   Also using this helps in computational cost reducing and detection of hyper parameters

5. Receptive Field, 

   The receptive field is defined as the region in the input space that a particular CNN's feature is looking at or is  been affected by.Within a receptive field, the closer a pixel to the center of the field, the more it contributes to the calculation of the output feature

6. SoftMax, 

   Softmax is a loss function used for multi-classification in logistic regression model .The probablitliies will be sum to 1 .Used in different layers of neural networks .The high value will have the higher probability than other values helping to predict the target values 

   Softmax measures how compatible a given set of parameters is with respect to the ground truth labels in the training dataset.

7. Learning Rate, 

   Learning rate is a hyper-parameter that controls how much we are adjusting the weights of our network with respect the loss gradient.The lower the value, the slower we travel along the downward slope.

   using a low learning rate is a good idea in terms of making sure that we do not miss any local minima, it could also mean that we’ll be taking a long time to converge — especially if we get stuck on a plateau region.

   The formula for LR is new_weight = existing_weight — learning_rate * gradient

8. Kernels and how do we decide the number of kernels?

   The number of kernels  as hyper-parameter can be used to tune the validation set .

   Depending on the task the number of kernels used can be change emperically .The more complex the dataset is the more number of kernels work better .

    Intuitively, number of kernel at layer layer expected to bigger in the previous layers, as number of possible combination grow. That is why, in general, first layer kernels are less than mid- high-level ones.

9. Batch Normalization, 

   Batch normalization is a technique for improving the speed, performance, and stability of artificial neural networks. Batch normalization was introduced in a 2015 paper. It is used to **normalize** the input layer by adjusting and scaling the activations.

   batch normalization normalizes the output of a previous activation layer by subtracting the batch mean and dividing by the batch standard deviation.

   Batch normalization reduces the amount by what the hidden unit values shift around (covariance shift)

10. Image Normalization, 

    In image processing, normalization is a process that changes the range of pixel intensity values. Applications include photographs with poor contrast due to glare, for example. Normalization is sometimes called contrast stretching or histogram stretching.

    The pixel values in images must be scaled prior to providing the images as input to a deep learning neural network model during the training or evaluation of the model.

11. Position of MaxPooling, 

    Maxpooling should be applied at a pointcuts  where a particular feature is detected .

    Normally the pointcuts used are edge detection,texture detection , colour constrast detection

    These should not be used at 1*1 filters as this would decrease the absolute required parameters

12. Concept of Transition Layers, 

    Usually defined as the many convolution intermediate layers added before maxpooling is performed on the data set 

13. Position of Transition Layer, 

    The transition layers are used to train the model up until the optimal recogniition is done .usually done at the point cut level

14. Number of Epochs and when to increase them, 

    Epochs is the iteration of model through all classes of dataset or the entire batch forward .

    The number of epochs depends on the validation accuracy . If the validation acc is stagnate after few epochs then the number can be stopped . If the acc is growing without overfitting or underfitting the test data in which increase the number of epochs will train the data better

    The number of epochs also varies with batch size smaller batches take lesser to epochs to achevie the normalised accuracy

15. DropOut

    Dropout is a technique where randomly selected neurons are ignored during training. They are “dropped-out” randomly.A Simple Way to Prevent Neural Networks from Overfitting .

    Always a dropout of 0.01 is proposed so as to not ignore the most relevent features

16. When do we introduce DropOut, or when do we know we have some overfitting

    when the loss is high , the training to test data acc increasing invariantly we know that the gap is high and hence the data is overfitting 

17. The distance of MaxPooling from Prediction, 

18. The distance of Batch Normalization from Prediction, 

19. When do we stop convolutions and go ahead with a larger kernel or some other alternative (which we have not yet covered)



    when the accuracy does not improve after regularisation we choose to go with a larger kernel 

20. How do we know our network is not going well, comparatively, very earlys 

    when the first 4 iterations/epochs stays stagnate or more or less in the same value , the average or mean of those values gives a fair idea of where the network is going .

    also how close is the acc is to validation accuracy gives a better picture 

21. Batch Size, and effects of batch size

    batch size should be determined by the number of classes within the image and computational efficiency of the network . Higher the number of classes then lesser batches can be used 

22. When to add validation checks

    By using call backs - LearningRateScheduler for LR 

    By verifying validation acc - validation_data=(X_test, Y_test)

23. LR schedule and concept behind it

    based on the acc , the scheduler auto tunes the learning ,This is preferred than setting manually LR after each epoch 

24. Adam vs SGD
