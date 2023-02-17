import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './order-details.reducer';

export const OrderDetailsDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const orderDetailsEntity = useAppSelector(state => state.orderDetails.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="orderDetailsDetailsHeading">Order Details</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{orderDetailsEntity.id}</dd>
          <dt>
            <span id="quantity">Quantity</span>
          </dt>
          <dd>{orderDetailsEntity.quantity}</dd>
          <dt>
            <span id="amount">Amount</span>
          </dt>
          <dd>{orderDetailsEntity.amount}</dd>
          <dt>Order</dt>
          <dd>{orderDetailsEntity.order ? orderDetailsEntity.order.id : ''}</dd>
          <dt>Product</dt>
          <dd>{orderDetailsEntity.product ? orderDetailsEntity.product.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/order-details" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/order-details/${orderDetailsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default OrderDetailsDetail;
