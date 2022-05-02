import React from "react";
import {Link} from "react-router-dom";

export function Product(props) {
    const id = props.id;
    const productName = props.name;
    const imgUrl = props.imgUrl;
    const price = props.price;

    // const handleButtonOnClick = e => {
    //     props.onAddClick(id);
    // };

    return (
        <>
            <div className="col-2"><img className="img-fluid" src={imgUrl} alt=""/></div>
            <div className="col-md-8 align-items-center">
                <div className="row text-muted fs-4"><Link to="/detail/{id}" state={{ data: props}}>{productName}</Link></div>
            </div>
            <div className="col text-center price fs-5">{price}원</div>
            {/*<div className="col text-end action">*/}
            {/*    <button onClick={handleButtonOnClick} className="btn btn-small btn-outline-dark">추가</button>*/}
            {/*</div>*/}
        </>
    )
}