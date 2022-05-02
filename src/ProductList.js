import React from "react";
import {Product} from "./Product";

export function ProductList({products = [], onBinAdd}) {

    return (
        <React.Fragment>
            <h5 className="flex-grow-0"><b>상품 목록</b></h5>
            <ul className="list-group products">
                {products.map(v =>
                    <li key={v.id} className="list-group-item d-flex mt-3">
                        <Product product={v} onBinAdd={onBinAdd} />
                    </li>
                )}
            </ul>
        </React.Fragment>
    )
}