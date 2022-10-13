/*
testing fibonacci functions
recursive method
bottom up method

comparing the time it takes to caclulate fibonacci number using recursion vs bottom up memorization (dynamic programming)
*/

var startTime, endTime;

function start() {
  startTime = new Date();
}

function end() {
  endTime = new Date();
  var timeDiff = endTime - startTime; //in ms
  // strip the ms
  timeDiff /= 1000;

  // get seconds
  var seconds = Math.round(timeDiff);
  console.log(seconds + " seconds");
}

function rFib(n) {
  if (n <= 1) {
    return n;
  }
  return rFib(n - 2) + rFib(n - 1);
}

let fibNum = [0, 1];

function buFib(n) {
  if (n <= 1) {
    return fibNum[n];
  }
  for (let i = 2; i <= n; i++) {
    fibNum[i] = fibNum[i - 2] + fibNum[i - 1];
  }
  return fibNum[n];
}

let n = 45;

start();
let a = rFib(n);
console.log("rFib(" + n + ") = " + a);
end();

start();
let b = buFib(n);
console.log("buFib(" + n + ") = " + b);
end();
