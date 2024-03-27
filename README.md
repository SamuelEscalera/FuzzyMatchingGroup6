1. Review if a tokenizer recognizes paths ("Android/android-L-preview/AndroidManifest.xml")
   and phone numbers.
    
    ### PHONE NUMBER
        a) If there is no tokenizer for any of them, implement it.
            The library already provides us with the tools to verify the match between two telephone numbers, but this tool only verifies 10-digit US telephone numbers (3 digits of the region code and 7 digits of the number in question).
            In order to use this tool but with Bolivian numbers, we have to make a small change, in certain methods, such as normalizing the data type, this method expects a data of 10 characters, in this method we have to make it expect 7 characters.
            The type of tokenizer used is centered for 10 characters, we have to modify it so that it tokenizes with 7 characters.
            In this way we have implemented PHONE MATCHING for Bolivian numbers

2. Review which matching algorithm is implemented.
Matching algorithm implemented in:

- a. Are there any other matchings that can be implemented?
   Another type of matching could be for a LINK or URL type element of a web page.
- b. If so, what will be the idea, and what will be its use?
   The idea would be to use Levenshtein to calculate the similarity between 2 URLs, this in general would be useful to 
  know if 2 URLs are the same or not and how similar they are. This could help us to know if there is a "domain phishing" attempt, for example:
   - www.google.com
   - www.gooogle,com
   Both URLs are similar but not the same, and the wrong URL could take you to a different page.
   If we compare the 2 URLs with levenshtein we could know that they are not the same, and this could help us with 
   longer or more complicated URLs.
   
c. If we need to add another matching algorithm, review and explain how that can be
     done.
ElementType class
1. First I would add a new type of element of type URL in the classType
2. I would add a preprocessing function for the URL element and assign it to the URL case in the getPreProcessFunction method.
3. Add a tokenizer function "urlTokenizer" and assign it to the URL case in the getTokenizerFunction method.

PreProcessFunctions class
1. Create the urlPreprocessing method.
2. The urlPreprocessing method will be in charge of processing the URL input.

TokenaizerFunction class
1. Create the urlTokenizer method
2. In this part we will compare our two inputs (e.g. URL1 URL2), to see the similarity.


The initial idea to implement the code for the three classes is in the folder "urlElement". 
