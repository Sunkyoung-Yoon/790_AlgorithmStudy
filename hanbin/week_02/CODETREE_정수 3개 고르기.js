const input = require('fs').readFileSync(0).toString().trim().split('\n');

const [n, m] = input[0].split(' ').map(Number);
const arr = input[1].split(' ').map(Number).sort((a,b) => a - b);

let answer = 0;

for (let i = 0; i < arr.length; i++){
    for (let left = 0; left < arr.length-1; left++){
        if (left === i) continue;

        for (right = left + 1; right < arr.length; right++){
            if (right === i) continue;

            let sum = arr[i] + arr[left] + arr[right];

            if (sum <= m && m - sum < m - answer){
                answer = sum;
            }
        }
    }
}

console.log(answer !== 0 ? answer : -1);
