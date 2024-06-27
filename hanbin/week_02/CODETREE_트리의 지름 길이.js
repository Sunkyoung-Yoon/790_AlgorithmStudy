const input = require('fs').readFileSync(0).toString().trim().split('\n');

class Edge {
    constructor(to, dist){
        this.to = to;
        this.dist = dist;
    }
}

function solution(){
    const N = Number(input[0]);
    const edges = input.slice(1).map((it) => it.split(' ').map(Number));
    const nodes = Array(N+1).fill(0).map((it) => []);

    for (let edge of edges){
        nodes[edge[0]].push(new Edge(edge[1], edge[2]));
        nodes[edge[1]].push(new Edge(edge[0], edge[2]));
    }

    const visited = Array(N+1).fill(-1);

    DFS(visited, nodes, 1);

    let start = getMaxValue(visited);
    visited.fill(-1);
    
    DFS(visited, nodes, start[1]);

    console.log(getMaxValue(visited)[0]);
}

function DFS(visited, nodes, cur){
    if (visited[cur] === -1){
        visited[cur]++;
    }

    for (let edge of nodes[cur]){
        if(visited[edge.to] !== -1) continue;

        visited[edge.to] = visited[cur] + edge.dist;
        DFS(visited, nodes, edge.to);
    }
}

function getMaxValue(visited){
    let max = 0;
    let maxIdx = 0;
    for (let i = 1; i < visited.length; i++){
        if (max < visited[i]){
            max = visited[i];
            maxIdx = i;
        }
    }
    return [max,maxIdx];
}

solution();
