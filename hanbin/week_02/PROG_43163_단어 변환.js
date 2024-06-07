function solution(begin, target, words) {
    const used = Array(words.length).fill(false);
    
    var answer = dfs(0, used, words, begin, target);
    
    return answer === 51 ? 0 : answer;
}

function dfs(depth, used, words, curWord, targetWord){
    if (curWord === targetWord) return depth;
    
    let res = 51;
    
    for (let i = 0; i < words.length; i++){
        if (used[i]) continue;
        
        if (diffCheck(curWord, words[i]) === 1){
            used[i] = true;
            res = Math.min(res, dfs(depth + 1, used, words, words[i], targetWord));
            used[i] = false;
        }
    }
    
    return res;
}

function diffCheck(word1, word2){
    let cnt = 0;
    
    for (let i = 0; i < word1.length; i++){
        cnt += word1[i] !== word2[i] ? 1 : 0;      
    }
    
    return cnt;
}
