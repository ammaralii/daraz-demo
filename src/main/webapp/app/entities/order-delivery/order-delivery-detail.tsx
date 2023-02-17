import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './order-delivery.reducer';

export const OrderDeliveryDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const orderDeliveryEntity = useAppSelector(state => state.orderDelivery.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="orderDeliveryDetailsHeading">Order Delivery</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{orderDeliveryEntity.id}</dd>
          <dt>
            <span id="deliveryDate">Delivery Date</span>
          </dt>
          <dd>
            {orderDeliveryEntity.deliveryDate ? (
              <TextFormat value={orderDeliveryEntity.deliveryDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="deliveryCharge">Delivery Charge</span>
          </dt>
          <dd>{orderDeliveryEntity.deliveryCharge}</dd>
          <dt>
            <span id="shippingStatus">Shipping Status</span>
          </dt>
          <dd>{orderDeliveryEntity.shippingStatus}</dd>
          <dt>Order</dt>
          <dd>{orderDeliveryEntity.order ? orderDeliveryEntity.order.id : ''}</dd>
          <dt>Shipping Address</dt>
          <dd>{orderDeliveryEntity.shippingAddress ? orderDeliveryEntity.shippingAddress.id : ''}</dd>
          <dt>Delivery Manager</dt>
          <dd>{orderDeliveryEntity.deliveryManager ? orderDeliveryEntity.deliveryManager.id : ''}</dd>
          <dt>Delivery Boy</dt>
          <dd>{orderDeliveryEntity.deliveryBoy ? orderDeliveryEntity.deliveryBoy.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/order-delivery" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/order-delivery/${orderDeliveryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default OrderDeliveryDetail;
