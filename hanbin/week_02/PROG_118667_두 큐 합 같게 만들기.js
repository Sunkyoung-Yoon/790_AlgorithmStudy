function solution(queue1, queue2) {
    const MAX_VALUE = 600_001;
    var answer = MAX_VALUE;

    const totalQueue = queue1.concat(queue2);

    let { left, right } = calBoundary(queue1);
    let { queue1Sum, queue2Sum } = init(left, right, totalQueue);

    if (
        (queue1Sum + queue2Sum) / 2 !==
        Math.round((queue1Sum + queue2Sum) / 2)
    ) {
        return -1;
    }

    let depth = 0;
    while (
        depth < MAX_VALUE 
    ) {
        if (queue1Sum < queue2Sum) {
            queue1Sum += totalQueue[(right + 1) % totalQueue.length];
            queue2Sum -= totalQueue[(right + 1) % totalQueue.length];
            right = (right + 1) % totalQueue.length;
        } else if (queue1Sum > queue2Sum){
            queue1Sum -= totalQueue[left];
            queue2Sum += totalQueue[left];
            left = (left + 1) % totalQueue.length;
        } else {
            break;
        }
        depth++;
    }
    
    return depth === MAX_VALUE ? -1 : depth;
}

function calBoundary(queue1) {
    let left = 0;
    let right = queue1.length - 1;

    return {
        left,
        right,
    };
}

function init(left, right, totalQueue) {
    let queue1Sum = 0,
        queue2Sum = 0;

    for (let i = 0; i < totalQueue.length; i++) {
        if (left <= i && i <= right) {
            queue1Sum += totalQueue[i];
            continue;
        }
        queue2Sum += totalQueue[i];
    }

    return {
        queue1Sum,
        queue2Sum,
    };
}
