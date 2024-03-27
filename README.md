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
