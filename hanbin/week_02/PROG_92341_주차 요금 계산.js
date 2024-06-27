function solution(fees, records) {
    var answer = [];
    
    const record = new Map();
    const result = new Map();
    
    for (data of records){
        let [inputTime, carNumber, info] = data.split(' ');
        const parsedTime = parseTime(inputTime);
        const calTime = handleRecord(parsedTime, carNumber, record);

        if (calTime === -1) continue;

        const prevData = result.get(carNumber) ?? 0;
        result.set(carNumber, prevData + calTime);
    }
    
    const calculatedFee = new Map();
    
    for (let [carNumber, time] of record){
        const LIMIT_TIME = 60 * 24 - 1;
        
        let curTime = LIMIT_TIME - record.get(carNumber);
        
        result.set(carNumber, (result.get(carNumber) ?? 0) + curTime);
    }
    
    for (let time of result){
        calculatedFee.set(time[0], calFee(time, fees, record));
    }
    
    
    const tmp = [...calculatedFee].sort((a, b) => a[0] - b[0]);
    for (let it of tmp){
        answer.push(it[1]);
    }
    
    return answer;
}

function parseTime(time){
    const [hour, minute] = time.split(':').map(Number);
    
    return hour * 60 + minute;
}

function handleRecord(time, carNumber, record){
    const hasRecord = record.has(carNumber);
    
    if (hasRecord){
        const data = record.get(carNumber);    
        record.delete(carNumber);
        return time - data;
    }
    
    record.set(carNumber, time);
    return -1;
}

function calFee([carNumber, parsedTime], [time, fee, unitTime, unitFee], record){
    if (parsedTime <= time) return fee;
    
    return fee + Math.ceil((parsedTime - time) / unitTime) * unitFee;
}

