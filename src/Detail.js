import React from "react";
import {useLocation} from "react-router-dom";

export function Detail() {
    const props = useLocation().state.data;

    const id = props.id;
    const productName = props.name;
    const imgUrl = props.imgUrl;
    const price = props.price;

    // const handleButtonOnClick = e => {
    //     props.onAddClick(id);
    // };

    return (
        <>
            <img className="img-fluid" src={imgUrl} alt="" height="1000" width="1000"/>
            {/*<div className="col">*/}
            {/*    <div className="row text-muted">{productName}</div>*/}
            {/*</div>*/}
            {/*<div className="col text-center price">{price}원</div>*/}
            {/*<div className="col text-end action">*/}
            {/*    <button onClick={handleButtonOnClick} className="btn btn-small btn-outline-dark">추가</button>*/}
            {/*</div>*/}
        </>
    )
}