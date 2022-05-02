import './App.css';
import React, {useEffect, useState} from "react";
import {
    BrowserRouter as Router,
    Route,
    Routes,
} from 'react-router-dom'

import 'bootstrap/dist/css/bootstrap.css'
import axios from "axios";

import {ProductList} from "./ProductList";
import {Detail} from "./Detail";


function Main() {
    const [products, setProducts] = useState([
        {id : 'uuid-1', name: '콜롬비아 커피1', imgUrl : 'https://i.imgur.com/HKOFQYa.jpeg', price :3000},
        {id : 'uuid-2', name: '콜롬비아 커피2', imgUrl : 'https://i.imgur.com/HKOFQYa.jpeg', price :3000},
        {id : 'uuid-3', name: '콜롬비아 커피3', imgUrl : 'https://i.imgur.com/HKOFQYa.jpeg', price :3000},
    ]);

    useEffect(() => {
        axios.get("http://localhost:8080/api/v1/products")
            .then(v => setProducts(v.data));
    }, []);

  return (
      <div className="container-fluid">
        <div className="row justify-content-center m-4">
          <h1 className="text-center"> 배민 상회 </h1>
        </div>
        <div className="card">
          <div className="row">
            <div className="col mt-4 d-flex flex-column align-items-start p-3 pt-0">
                <ProductList products={products} />
            </div>
          </div>
        </div>
      </div>
  );
}

export default function App(){
    return (
        <Router>
            <Routes>
                <Route exact path='/' element={<Main/>} />
                <Route exact path='/detail/*' element={<Detail/>} />
            </Routes>
        </Router>
    );
};
