import React, { createContext, useContext, useState } from "react";

const ScoreContext = createContext();

export function useScore() {
  return useContext(ScoreContext);
}

export function ScoreProvider({ children }) {
  const [lastScore, setLastScore] = useState(100);

  const updateScore = (newScore) => {
    setLastScore(newScore);
  };

  return (
    <ScoreContext.Provider value={{ lastScore, updateScore }}>
      {children}
    </ScoreContext.Provider>
  );
}
