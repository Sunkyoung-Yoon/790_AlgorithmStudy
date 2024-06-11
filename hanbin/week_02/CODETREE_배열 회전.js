const input = require('fs').readFileSync(0).toString().trim().split('\n');

// 반례 못찾음

const [n, m, k] = input[0].split(' ').map(Number);
let arr = input.slice(1).map((it) => it.split(' ').map(Number));

function solution(){
    for (let i = 0; i < k; i++){
        arr = rotate(arr,n,m);
    }

    console.log(arr.map((it) => it.join(' ')).join('\n'));
}


function rotate(arr, n, m){
    const tmp = arr.map((it) => [...it]);

    for (let r = 0, c = 0, i = 1; r < Math.ceil(n/2) && c < Math.ceil(m/2); r++, c++, i++){
        while(c < m - i){
            tmp[r][c] = arr[r][c+1];
            c++;
        }

        while(r < n - i){
            tmp[r][c] = arr[r+1][c];
            r++;
        }

        while(c >= i){
            tmp[r][c] = arr[r][c-1];
            c--;
        }

        while(r >= i){
            tmp[r][c] = arr[r-1][c];
            r--;
        }
    }

    return tmp;
}

solution();
