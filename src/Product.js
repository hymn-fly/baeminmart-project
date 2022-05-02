import React from "react";
import {Link} from "react-router-dom";

export function Product({product = {}, onBinAdd}) {
    const id = product.id;
    const productName = product.name;
    const imgUrl = product.imgUrl;
    const price = product.price;

    // const handleButtonOnClick = e => {
    //     product.onAddClick(id);
    // };

    return (
        <>
            <div className="col-2"><img className="img-fluid" src={imgUrl} alt="" /></div>
            <div className="col-md-8 align-items-center">
                <div className="row text-muted fs-4"><Link to="/detail/{id}" state={{ data: product, event : onBinAdd}}>{productName}</Link></div>
            </div>
            <div className="col text-center price fs-5">{price}원</div>
            {/*<div className="col text-end action">*/}
            {/*    <button onClick={handleButtonOnClick} className="btn btn-small btn-outline-dark">추가</button>*/}
            {/*</div>*/}
        </>
    )
}