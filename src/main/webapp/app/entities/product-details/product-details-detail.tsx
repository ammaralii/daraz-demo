import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './product-details.reducer';

export const ProductDetailsDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const productDetailsEntity = useAppSelector(state => state.productDetails.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="productDetailsDetailsHeading">Product Details</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{productDetailsEntity.id}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{productDetailsEntity.description}</dd>
          <dt>
            <span id="imageUrl">Image Url</span>
          </dt>
          <dd>{productDetailsEntity.imageUrl}</dd>
          <dt>
            <span id="isavailable">Isavailable</span>
          </dt>
          <dd>{productDetailsEntity.isavailable ? 'true' : 'false'}</dd>
          <dt>Product</dt>
          <dd>{productDetailsEntity.product ? productDetailsEntity.product.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/product-details" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/product-details/${productDetailsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default ProductDetailsDetail;
