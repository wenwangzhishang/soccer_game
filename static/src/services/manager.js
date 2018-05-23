import axios from 'axios';

let Manager = {
  registerTeams() {
    return axios.put('/api/teams');
  },

  registerPitches() {
    return axios.put('/api/pitches');
  },

  registerJudges() {
    return axios.put('/api/judges');
  },

  fetchTeams() {
    return axios.get('/api/teams');
  },

  fetchPitches() {
    return axios.get('/api/pitches');
  },

  fetchJudges() {
    return axios.get('/api/judges');
  },

  fetchMatchesByDay(day) {
    return axios
      .get(`/api/matches/${day}`)
      .then((res) => {
        let potRes = res.data;
        potRes.data = potRes
          .data
          .map(item => {
            let map = {}
            map.team1Points = getTeamTotalCounts(item.matchResultMessage, item.team1)
            map.team2Points = getTeamTotalCounts(item.matchResultMessage, item.team2)
            map.pitch = item.pitch.name
            map.type = item.type
            map.team1 = item.team1.team_name
            map.team2 = item.team2.team_name
            map.duration = item.matchResultMessage[0].duration
            return map
          })
        return Promise.resolve(potRes)
      })
  }
}

function getTeamTotalCounts(arr, team) {
  return arr
    .filter(e => e.team === team.team_name)
    .reduce((acc, cur) => {
      acc.shotCount += cur.shotCount
      return acc;
    })
    .shotCount
}
export default Manager;
