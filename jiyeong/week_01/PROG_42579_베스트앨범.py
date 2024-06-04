def solution(genres, plays):
    answer = []
    gMap = dict()
    N = len(plays)

    # 각 장르별로 노래 구분하기 => {장르 : [(재생횟수, 고유번호)]}
    for i in range(N):
        if genres[i] not in gMap.keys():
            gMap[genres[i]] = [(plays[i], i)]
        else:
            gMap[genres[i]].append((plays[i], i))
    # print(gMap)

    # 장르별 총 재생횟수 구하기 => [(총재생횟수, 장르명)]
    gList = []
    for k in gMap.keys():
        pSum = 0
        for p in gMap[k]:
            pSum += p[0]
        gList.append((pSum, k))
    gList.sort(key=lambda x: x[0], reverse=True)

    for i in range(len(gList)):
        pList = gMap[gList[i][1]]
        # print(pList)
        if len(pList) == 1:
            answer.append(pList[0][1])
            continue
        pList.sort(key=lambda x: x[0], reverse=True)
        answer.append(pList[0][1])
        answer.append(pList[1][1])

    return answer