import React from "react";
import { Link } from "react-router-dom";
import "../css/policy.css";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import Sidebar from "../sidebar/sidebar";

const Policy = () => {
  return (
    <div className="questionJ-page" id="questionJ-container">
      <div className="questionJ-devide">
        <div className="questionJ-Upside">
          <div className="questionJ-title">
            <h2>정책 정보</h2>
          </div>
          <div className="policyJ-category">
            <div>
              <input type="radio" name="category" value="노년층" />{" "}
              <span>노년층</span>
            </div>
            <div>
              <input type="radio" name="category" value="사회취약층" />{" "}
              <span>사회취약층</span>
            </div>
            <div>
              <input type="radio" name="category" value="농어촌" />{" "}
              <span>농어촌</span>
            </div>
            <div>
              <input type="radio" name="category" value="장애인" />{" "}
              <span>장애인</span>
            </div>
          </div>
        </div>
        <div className="questionJ-table" border>
          <TableContainer component={Paper}>
            <Table aria-label="simple table">
              <TableHead>
                <TableRow>
                  <TableCell align="left">정책명</TableCell>
                  <TableCell align="center">정책내용요약</TableCell>
                  <TableCell align="right">대상자</TableCell>
                  <TableCell align="right">디데이</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                <TableRow muiTablePaperProps>
                  <TableCell>1</TableCell>
                  <TableCell align="center">2</TableCell>
                  <TableCell align="right">3</TableCell>
                  <TableCell align="right">3</TableCell>
                </TableRow>
                <TableRow>
                  <TableCell>1</TableCell>
                  <TableCell align="center">2</TableCell>
                  <TableCell align="right">3</TableCell>
                  <TableCell align="right">3</TableCell>
                </TableRow>
                <TableRow>
                  <TableCell>1</TableCell>
                  <TableCell align="center">2</TableCell>
                  <TableCell align="right">3</TableCell>
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
export default Policy;
