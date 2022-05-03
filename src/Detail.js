import React, {useState} from "react";
import {useLocation} from "react-router-dom";
import axios from "axios";

export function OrderForm({handleEmailInputChanged, handleAddressInputChanged, handleNameInputChanged, order}){
    return (
        <>
            <form>
                <div className="mb-3">
                    <label htmlFor="email" className="form-label">이메일</label>
                    <input type="email" className="form-control mb-1" value={order.email}
                           onChange={handleEmailInputChanged} id="email"/>
                </div>
                <div className="mb-3">
                    <label htmlFor="address" className="form-label">주소</label>
                    <input type="text" className="form-control mb-1" value={order.address}
                           onChange={handleAddressInputChanged} id="address"/>
                </div>
                <div className="mb-3">
                    <label htmlFor="postcode" className="form-label">이름</label>
                    <input type="text" className="form-control" value={order.name}
                           onChange={handleNameInputChanged} id="postcode"/>
                </div>
                <div>당일 오후 2시 이후의 주문은 다음날 배송을 시작합니다.</div>
            </form>
        </>
    )
}


export function Detail() {
    const props = useLocation().state.data;
    const id = props.id;
    const productName = props.name;
    const imgUrl = props.imgUrl;
    const productPrice = props.price;

    const [order, setOrder] = useState({
        email: "", address: "", name: ""
    });
    const handleEmailInputChanged = (e) => {
        setOrder({...order, email: e.target.value});
    }
    const handleAddressInputChanged = (e) => {
        setOrder({...order, address: e.target.value});
    }
    const handleNameInputChanged = (e) => {
        setOrder({...order, name: e.target.value});
    }

    const onOrderSubmit = (order) => {
        axios.post("http://localhost:8080/api/v1/orders", {
            email : order.email,
            address: order.address,
            name: order.name,
            items: [{
                productId : id,
                productCount: obj.count
            }]
        }).then(
            v => alert("주문이 정상적으로 접수되었습니다."),
            e => {
                alert("서버 장애");
                console.error(e);
            })
        console.log(order, {
            productId : id,
            productCount: obj.count
        });
    }

    const handleSubmit = (e) => {
        if (order.address === "" || order.email === "" || order.postcode === "") {
            alert("입력값을 확인해주세요!")
        } else {
            onOrderSubmit(order);
        }
    }

    const [obj, setCount] = useState({
        count: 0,
    });
    const plusCount = (e) => {setCount({...obj, count: obj.count + 1});}
    const minusCount = (e) => {setCount({...obj, count: obj.count > 0 ? obj.count -1 : 0});}


    // const handleButtonOnClick = e => {
    //     props.onAddClick(id);
    // };

    return (
        <>
            <div className="card">
                <div className="row">
                    <div className="col-md-7 mt-4 p-3 pt-0">
                        <img className="img-fluid" src={imgUrl} alt="" />
                    </div>
                    <div className="col summary p-4">
                        <div>
                            <h4 className="m-0 p-0 "><b>{productName}</b></h4>
                            <div className="row pt-2 pb-2 border-top">
                                <h7 className="col"><b>배송 정보</b></h7>
                                <h7 className="col text-lg-start">든든 배송 상품 총 30,000원 이상 주문 시 구매가능</h7>
                            </div>
                        </div>
                        <hr/>
                        <ul>
                            <li>
                                <div className="row">
                                    <h7 className="col">제공 수량</h7>
                                    <h7 className="col text-lg-start">1ea</h7>
                                </div>
                            </li>
                            <li>
                                <div className="row">
                                    <h7 className="col">기준 단가</h7>
                                    <h7 className="col text-lg-start">개당 {productPrice.toLocaleString()}원</h7>
                                </div>
                            </li>
                            <li>
                                <div className="row">
                                    <h7 className="col">원산지</h7>
                                    <h7 className="col text-lg-start">상품정보고시 참조</h7>
                                </div>
                            </li>
                        </ul>
                        <hr/>
                        <div className="row">
                            <h7 className="col">주문 수량</h7>
                            <div className="btn-group" role="group">
                                <input type='button'
                                       onClick={minusCount}
                                       value='-'/>
                                <div id='result'>{obj.count}</div>
                                <input type='button'
                                       onClick={plusCount}
                                       value='+'/>
                            </div>
                        </div>
                        <hr/>
                        <OrderForm
                            handleEmailInputChanged={handleEmailInputChanged}
                            handleAddressInputChanged={handleAddressInputChanged}
                        handleNameInputChanged={handleNameInputChanged}
                        order={order}/>
                        <hr/>
                        <div>당일 오후 2시 이후의 주문은 다음날 배송을 시작합니다.</div>
                        <div className="row pt-2 pb-2 border-top">
                            <h5 className="col">총금액</h5>
                            <h5 className="col text-end">{(obj.count * productPrice).toLocaleString()}원</h5>
                        </div>
                        <button className="btn btn-dark col-12" onClick={handleSubmit}>주문하기</button>
                    </div>
                </div>
            </div>
        </>
    )
}