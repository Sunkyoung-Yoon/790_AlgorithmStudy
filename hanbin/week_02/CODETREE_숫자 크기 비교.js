const input = require('fs').readFileSync(0).toString().trim().split('\n');

// 사전순 정렬 실패

class Queue {
    constructor(){
        this.storage = {};
        this.front = -1;
        this.rear = -1;
    }

    push(value){
        this.storage[++this.rear] = value;
    }

    poll(){
        if (this.isEmpty()) {
            return null;
        }
        
        const value = this.storage[++this.front];
        delete this.storage[this.front - 1];
        return value;
    }

    isEmpty(){
        return this.front === this.rear;
    }
}

function solution(){
    const [N, M] = input[0].split(' ').map(Number);
    const nodes = Array(N+1).fill(0).map(() => []);
    const degrees = Array(N+1).fill(0);

    for (let i = 1; i < input.length; i++){
        const cur = input[i].split(' ').map(Number);
        nodes[cur[0]].push(cur[1]);
        degrees[cur[1]]++;
    }

    let startPoint = new Queue();

    for (let i = 1; i < degrees.length; i++){
        if (degrees[i] === 0){
            startPoint.push([i, 0]);
        }
    }

    console.log(sort(nodes, degrees, startPoint));
}

function sort(nodes, degrees, queue){
    let order = [];
    
    while(!queue.isEmpty()){
        const cur = queue.poll();

        for (let node of nodes[cur[0]]){
            degrees[node]--;

            if (degrees[node] === 0){
                queue.push([node, cur[1] + 1]);
            }
        }

        order.push(cur);
    }

    order.sort((a, b) => {
        if (a[1] === b[1]){
            return a[0] - b[0]
        }
        return a[1] - b[1];
    })
    
    return order.map((it) => it[0]).join(' ');
}

solution();
