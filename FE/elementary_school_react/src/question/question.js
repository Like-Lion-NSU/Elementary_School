import React from "react";
import { Link } from "react-router-dom";
import "../css/question.css";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import Sidebar from "../sidebar/sidebar";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSearch } from "@fortawesome/free-solid-svg-icons";

const Question = () => {
  return (
    <div className="questionJ-page" id="questionJ-container">
      <div className="questionJ-devide">
        <div className="questionJ-Upside">
          <div className="questionJ-title">
            <h2>소통해요</h2>
          </div>
          <div className="questionJ-search">
            <h2 style={{ color: "black" }}>
              <FontAwesomeIcon icon={faSearch} />
            </h2>
            <input className="search" placeholder="검색어를 입력하세요" />
          </div>
        </div>
        <div className="questionJ-table" border>
          <TableContainer component={Paper}>
            <Table aria-label="simple table">
              <TableHead>
                <TableRow>
                  <TableCell align="left">게시물 제목</TableCell>
                  <TableCell align="center">작성자</TableCell>
                  <TableCell align="right">조회수</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                <TableRow muiTablePaperProps>
                  <TableCell>1</TableCell>
                  <TableCell align="center">2</TableCell>
                  <TableCell align="right">3</TableCell>
                </TableRow>
                <TableRow>
                  <TableCell>1</TableCell>
                  <TableCell align="center">2</TableCell>
                  <TableCell align="right">3</TableCell>
                </TableRow>
                <TableRow>
                  <TableCell>1</TableCell>
                  <TableCell align="center">2</TableCell>
                  <TableCell align="right">3</TableCell>
                </TableRow>
              </TableBody>
            </Table>
          </TableContainer>
        </div>
      </div>
      <div className="questionJ-sidebar">
        <Sidebar />
      </div>
    </div>
  );
};
export default Question;
