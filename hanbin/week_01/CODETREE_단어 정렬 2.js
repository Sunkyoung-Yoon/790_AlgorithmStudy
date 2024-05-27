let [n, ...arr] = require('fs').readFileSync(0).toString().trim().split('\n');

n = Number(n);

arr.sort();
arr.sort((a, b) => a.length - b.length);


console.log(arr.join('\n'));
