// 풀이 전략: 규칙 찾아서 O(1)에 처리

const N = Number(require('fs').readFileSync(0).toString().trim());

const arr = [0, 3, 5, 6, 9, 10, 12, 15];

let idx  = N % 7;
let mult = Math.floor(N / 7);
console.log(arr[idx]+ 15 * mult);
