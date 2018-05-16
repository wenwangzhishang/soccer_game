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
  }
}

export default Manager;
