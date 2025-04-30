/*
Implement a JavaScript ES6 module named AdvancedFactorialTool that calculates 
factorials of numbers provided by the user. The implementation should 
integrate the following ES6 features:

1. Implement your own ES6 class named CustomMap to mimic JavaScript's built-in Map.
2. Use the above-created CustomMap explicitly for caching factorial results to 
   avoid redundant calculations.
3. Provide a deepClone function to clone the cache object deeply, protecting the 
   internal state from accidental external modifications.
4. Implement a generator named factorialGenerator(n) which yields factorial 
   results sequentially from 1! to n!.

*/


class CustomMap {
    // write your code here
    constructor(){
        this.items = {};
    }
    set(key,value){
        if(typeof key === "object" ){
            key = JSON.stringify(key);
        }
        this.items[key] = value;
    }
    get(key){
        if(typeof key === "object" ){
            key = JSON.stringify(key);
        }
        return this.items[key];
    }
    has(key){
        if(typeof key === "object" ){
            key = JSON.stringify(key);
        }
        return this.items.hasOwnProperty(key);
    }
  }
  
  
  const AdvancedFactorialTool = {
      cache : new CustomMap(),
    factorialMemoized(n) {
      // write your code here
      if(n<=1){
          AdvancedFactorialTool.cache.set(n,1);
          return 1;
      }
      if(AdvancedFactorialTool.cache.has(n)){
          return AdvancedFactorialTool.cache.get(n);
      }
      const res = n*this.factorialMemoized(n-1);
      AdvancedFactorialTool.cache.set(n,res);
      return res;
    },
  
    deepClone(obj) {
        // write your code here
        if(typeof obj !== "object"){
          return obj;
        }
        if(Array.isArray(obj)){
          return obj.map(deepClone);
        }
        const clonedObj = new CustomMap();
        for(const key in obj.items){
            clonedObj[key] = this.deepClone(obj[key]);
        }
        return clonedObj;
    },
  
    *factorialGenerator(n) {
      // write your code here
      let a = 0;
      let fact = 1;
      while(a<n){
          a = a+1;
          fact = fact*a;
          yield fact;
      }
    }
  };
  
  
  
  
  
  
  
  
  
  //Testing code
  function testFactorialTool(n) {
    console.log(`Factorial of ${n} (Memoized):`, AdvancedFactorialTool.factorialMemoized(n));
  
    const originalCache = AdvancedFactorialTool.cache;
    const clonedCache = AdvancedFactorialTool.deepClone(originalCache);
    clonedCache.set(n, "modified");
    console.log("Original cache after clone modification:", originalCache.get(n));
  
    console.log(`Factorial sequence up to ${n}:`, [...AdvancedFactorialTool.factorialGenerator(n)]);
  }
  
  const readline = require('readline').createInterface({
    input: process.stdin,
    output: process.stdout
  });
  
  readline.question('Enter a positive integer to calculate factorial: ', input => {
    const n = parseInt(input.trim());
  
    if (isNaN(n) || n <= 0) {
      console.log('Please enter a valid positive integer.');
    } else {
      testFactorialTool(n);
    }
  
    readline.close();
  });
  