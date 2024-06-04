// 풀이 전략: 세그먼트리 활용
// 근데 배열을 한번 쭉 업데이트하고 그 다음에 조회만 쭉 하다보니까 누적합이 더 효율적이에요

let [input, ...queries] = require('fs').readFileSync(0).toString().trim().split('\n');

const [N, R, T] = input.split(' ').map(Number);

const tree = Array(N * 4).fill(0);
const lazy = Array(N * 4).fill(0);

for (let [L, R] of queries.map((it) => it.split(' ').map(Number))){
    update(1, 1, N, L, R);
}

let answer = [];

for (let i = 1; i <= N; i++){
    const res = query(1, 1, N, i);

    if (res === T){
        answer.push(i);
    }
}

console.log(answer.length > 0 ? answer.join(' ') : -1);

function lazyUpdate(node, start, end){
    if (lazy[node] !== 0){
        tree[node] += (end - start + 1) * lazy[node];

        if (start !== end){
            lazy[node * 2] += lazy[node];
            lazy[node * 2 + 1] += lazy[node];
        }

        lazy[node] = 0;
    }
}

function update(node, start, end, left, right){
    lazyUpdate(node, start, end);

    if (end < left || right < start){
        return;
    }

    if (left <= start && end <= right){
        lazy[node] = 1;

        lazyUpdate(node, start, end);
        return;
    }

    const mid = Math.floor((start + end) / 2);
    update(node * 2, start, mid, left, right);
    update(node * 2 + 1, mid+1, end, left, right);
    tree[node] = tree[node * 2] + tree[node * 2 + 1];
}

function query(node, start, end, idx){
    lazyUpdate(node, start, end);

    if (end < idx || idx < start){
        return 0;
    }

    if (start === end){
        return tree[node];
    }

    const mid = Math.floor((start + end) / 2);
    let l = query(node * 2, start, mid, idx);
    let r = query(node * 2 + 1, mid + 1, end, idx);
    return l + r;
}
